# KtValorantApi

Kotlin API Wrapper for Hendrik's Valorant API

https://github.com/Henrik-3/unofficial-valorant-api v4.0.0

A lot of the data models are provided through https://dash.valorant-api.com/

These are provided by my other library: [ValorantModelApi](https://github.com/CalMWolfs/ValorantModelApi)

This API Wrapper is a work in progress and is not yet complete, please make issues for any bugs.

While this library can be used in Java, it is primarily designed for Kotlin.

It is free to use and open source.

## Installation

Add this to your dependencies in your build.gradle.kts file:

```kts
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.CalMWolfs:KtValorantApi:1.0.0")
}
```

## Introduction

The api requests are not threaded, so you will need to set that up yourself.

All api requests require an api key, which you can get from [Henrik's Discord Server]("https://discord.com/invite/X3GaVkX2YN)

### Setting up the API
 
Kotlin

```kotlin
fun main(args: Array<String>) {
    val valorantApi = KtValorantApi("API_KEY")
}
```
java

```java
public static void main(String[] args) throws IOException {
    KtValorantApi valorantApi = new KtValorantApi("API_KEY");
}
```
### Example: Getting Account ID

Kotlin
```kotlin
fun main(args: Array<String>) {
    val valorantApi = KtValorantApi("API_KEY")
    val accountInfo = valorantApi.getAccountDetails(PlayerName("username", "tag"))
    println(accountInfo.playerId)
}
```

Java
```java
public static void main(String[] args) throws IOException {
    KtValorantApi valorantApi = new KtValorantApi("API_KEY");
    AccountInfo accountInfo = valorantApi.getAccountDetails(new PlayerName("username", "tag"));
    System.out.println(accountInfo.getPlayerId());
}
```
