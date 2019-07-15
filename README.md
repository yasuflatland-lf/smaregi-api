# Smaregi API
[![Build Status](https://travis-ci.org/yasuflatland-lf/smaregi-api.svg?branch=master)](https://travis-ci.org/yasuflatland-lf/smaregi-api) [![Build status](https://ci.appveyor.com/api/projects/status/amno3uaps0146s00?svg=true)](https://ci.appveyor.com/project/yasuflatland-lf/smaregi-api) [![Coverage Status](https://coveralls.io/repos/github/yasuflatland-lf/smaregi-api/badge.svg?branch=master)](https://coveralls.io/github/yasuflatland-lf/smaregi-api?branch=master)


Smaregi REST API Java wrapper with simple JSON mappings.

## Required Enviroment
* Smaregi customer account (Smaregi API is only exposed for [Smaregi](https://smaregi.jp/) customers)
* gradle 5.5 <=
* Oracle / Open JDK Java 8 =<

### Version Compatibility
Smaregi API version below complies Extenal Interface specifications (POS) document distributed by [Smaregi K.K](https://smaregi.jp/).

| Smaregi API version | this library version |
| :--- | :--- |
| 3.10.0 | 0.1 |  

## Usage
For the detailed usage, please see [SmaregiClientRealTest.groovy](https://github.com/yasuflatland-lf/smaregi-api/blob/develop/src/test/groovy/jp/creollc/smaregi/impl/SmaregiClientRealTest.groovy) The basic samples are as below:

In `build.gradle` please add below.
```java
repositories {
    mavenCentral()
    maven {
        url "https://raw.githubusercontent.com/yasuflatland-lf/smaregi-api/master/releases/"
    }
}

dependencies {
    compile group: 'jp.creollc', name: 'smaregi-api', version: '0.1'
}
```

## Limitations (As of July 2019)
* This API complies Smaregi's limitation. Please note that some APIs may not be available according to your contract with Smaregi.
* Smaregi API restricts the number of records for reference up to **500**. In case you configure a larger number, Smaregi API will simply return 500 records.
* Same as a reference, amount of records for the update at once is limited up to **1000**. 
* This library have been tested only handful APIs that I use for my current project, which is included in [SmaregiClientRealTest.groovy](/Users/yasuflatland/project/smaregi-api/src/test/groovy/jp/creollc/smaregi/impl/SmaregiClientRealTest.groovy). If you use this API other than test cases and find bugs, any PR for adding new tests and bug reports in the issue will be highly appreciated.

### Fetch Product Sample
The sample below is fetching up to **500** items of Product list. The offset (page) is 1. In case the product items more than 500, to fetch items from 101 to 501, change ```page``` parameter to 2.

```java
SmaregiRequest request 
    = RefRequestBuilder.builder()
        .procName(SmaregiConstants.CMD_PRODUCT_REF)
        .condition(StockConstants.PRODUCT_ID, "your-product-id-is-here")
        .tableName(ProductConstants.TABLE_NAME)
        .limit(500)
        .page(1)
        .build();

SmaregiClient client = new SmaregiClient("your-x_contact_id-string-here", "your-x_access_token-string-here");

SmaregiResponse result = client.execute(request);
```

### Stock Update Sample

Java object version of fetching Stock update sample is below. In case you want to update multiple stock at once, add ```UpdData``` object in the ```updData``` list. You can update up to **1000** items at once.

```java
List<UpdData> updData = new ArrayList() {
  {
    List<UpdRow> updRows = new ArrayList() {{
      add(new UpdRow( new HashMap<String, String>() {{
        put(StockConstants.STORE_ID, "your-store-id-is-here");
        put(StockConstants.PRODUCT_ID, "your-product-id-is-here");
        put(StockConstants.STOCK_AMOUNT, "1");
        put(StockConstants.STOCK_DIVISION, StockConstants.STOCK_DIVISION_DEFAULT);
      }}))
    }};
    add(new UpdData(StockConstants.TABLE_NAME, updRows));
  }
};

SmaregiRequest request = 
  UpdRequestBuilder.builder()
    .procName(SmaregiConstants.CMD_STOCK_UPD)
    .procDivision(SmaregiConstants.PROCESS_UPDATE)
    .procDetailDivision(SmaregiConstants.PROCESS_DIVISION_ABSOLUTE)
    .data(updData)
    .build();

SmaregiClient client = new SmaregiClient("your-x_contact_id-string-here", "your-x_access_token-string-here");

SmaregiResponse result = client.execute(request);
```

### Stock Update with Chain Methods Sample

The chain methods is Shuger Syntex of Java object version. ```updData``` method can be called multiple times.

```java
List<UpdData> updData =
    UpdDataBuilder
        .builder()
        .updData(StockConstants.TABLE_NAME,
            StockConstants.STORE_ID, "your-store-id-is-here",
            StockConstants.PRODUCT_ID, "your-product-id-is-here",
            StockConstants.STOCK_AMOUNT, "3",
            StockConstants.STOCK_DIVISION, StockConstants.STOCK_DIVISION_DEFAULT
        ).build();

SmaregiRequest request = UpdRequestBuilder.builder()
    .procName(SmaregiConstants.CMD_STOCK_UPD)
    .procDivision(SmaregiConstants.PROCESS_UPDATE)
    .procDetailDivision(SmaregiConstants.PROCESS_DIVISION_ABSOLUTE)
    .data(updData)
    .build();

SmaregiClient client = new SmaregiClient("your-x_contact_id-string-here", "your-x_access_token-string-here");

SmaregiResponse result = client.execute(request);

```

### Category Reference Sample
Category reference sample. The search term parameter can have ```like``` phrase for text fields.

```java
List<Map<String, String>> conditions = new ArrayList() {
  {
    add(new HashMap() {
      {
          put(CategoryConstants.CATEGORY_NAME + " like", "%沖縄%");
      }
    });
  }
}

List<String> fields = new ArrayList() {{
  add(CategoryConstants.CATEGORY_ID);
  add(CategoryConstants.CATEGORY_NAME);
}}

List<String> order = new ArrayList() {{
    add(CategoryConstants.CATEGORY_ID + " " + SmaregiConstants.SEARCH_ORDER_DESC)
}}

SmaregiRequest request = 
  RefRequestBuilder.builder()
    .procName(SmaregiConstants.CMD_CATEGORY_REF)
    .tableName(CategoryConstants.TABLE_NAME)
    .conditions(conditions)
    .fields(fields)
    .order(order)
    .limit(500)
    .page(1)
    .build();

SmaregiClient client = new SmaregiClient("your-x_contact_id-string-here", "your-x_access_token-string-here");

SmaregiResponse result = client.execute(request);
```

## How to build smaregi-api on your own
First of all, please clone this repository.

### Compile library
Go to the root folder and run `./gradlew assemble` or `gradle assemble`

### How to run a test
**SmaregiClientRealTest.groovy** includes test against real **Smaregi API server**. In case you want to run tests against real APIs, please comment out `Unroll` annotation and configure enviroment valuable, both **X_contract_id** and **X_access_token**. Tests in this file read Smaregi API tokens from the enviroment values.

Go to the root folder and run `./gradlew test --info` or `gradle test --info`

### How to publish the library for the public?
This library currently publishes the jar localy until it becomes stable. For publishing localy,

Go to the root folder and run `./gradlew publish` or `gradle publish`. The maven repository will be published under `release` directory.

### How to publish this library for a local repository? (.m2)?
Run `gradle publishMyLibraryPublicationToMavenLocal` or `gradlew publishMyLibraryPublicationToMavenLocal`