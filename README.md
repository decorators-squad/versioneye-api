## versioneye-api
[![Build Status](https://travis-ci.org/decorators-squad/versioneye-api.svg?branch=master)](https://travis-ci.org/decorators-squad/versioneye-api) [![Coverage Status](https://coveralls.io/repos/github/decorators-squad/versioneye-api/badge.svg?branch=master)](https://coveralls.io/github/decorators-squad/versioneye-api?branch=master)
[![PDD status](http://www.0pdd.com/svg?name=decorators-squad/versioneye-api)](http://www.0pdd.com/p?name=decorators-squad/versioneye-api)

[![DevOps By Rultor.com](http://www.rultor.com/b/decorators-squad/versioneye-api)](http://www.rultor.com/p/decorators-squad/versioneye-api)
[![We recommend IntelliJ IDEA](http://amihaiemil.github.io/images/intellij-idea-recommend.svg)](https://www.jetbrains.com/idea/)


Elegant OOP wrapper over the VersionEye API

### RIP VersionEye:

Apparently VersionEye will shut down by the end of 2017: https://blog.versioneye.com/2017/10/19/versioneye-sunset-process/
There is no point in continuing development here. This repository remains, however, as guidance/documentation for future Java REST clients.

## Design

This library is 100% object oriented, based on interfaces and final classes. It has one single entry point, [RtVersionEye](https://github.com/decorators-squad/versioneye-api/blob/master/src/main/java/com/amihaiemil/versioneye/RtVersionEye.java), which is the only public class. Everything is encapsulated, wrapped in intuitive interfaces. The main goal is to offer a well organized, [user-friendly](http://www.baeldung.com/design-a-user-friendly-java-library) library that's easy to use.

Behind the scenes the [jcabi-http](https://www.github.com/jcabi/jcabi-http) client is used, mainly for the convenient and fluent API that it offers.
Json is manipulated using javax.json API with the Glassfish [implementation](https://mvnrepository.com/artifact/org.glassfish/javax.json).

For unit-testing, a [mock version](https://github.com/decorators-squad/versioneye-api/issues/13) is offered, which holds a JsonBuilder behind, instead of making real HTTP requests.

## Usage
There is one single entry point and the usage is fluent. Everyone should be able to start using this library in a matter of minutes:
1. Pinging:

```java
    VersionEye api = new RtVersionEye();
    JsonObject json = api.services().ping();
    MatcherAssert.assertThat(
        json.getBoolean("success"), Matchers.is(true)
    );
    MatcherAssert.assertThat(
        json.getString("message"), Matchers.is("pong")
    );
```

2. Getting information about me (the authenticated used):

```java
    Authenticated user = new RtVersionEye("...token...").me().about();
    MatcherAssert.assertThat(
        user.fullName(), Matchers.is("Mihai Emil Andronache")
    );
    MatcherAssert.assertThat(
        user.username(), Matchers.is("amihaiemil")
    );
```
3. Getting a user's comments (same for Favorites):

```java
    VersionEye versionEye = new RtVersionEye("...token...");
    Comments comments = versionEye.users().user("amihaiemil").comments();
    List<Comment> firstPage = comments.fetch(1);//first page of comments
    for(Page<Comment> page : comments.paginated()) {
        //iterate over all the comments pages (including the first one)
        List<Comment> onPage = page.fetch();
    }
```


@todo #9:15min/DEV Add more examples of usage here

## Contribute

Contributors are [welcome](http://www.amihaiemil.com/2016/12/30/becoming-a-contributor.html)

1. Open an issue regarding an improvement you thought of, or a bug you noticed, or ask to be assigned to an existing one.
2. If the issue is confirmed, fork the repository, do the changes on a separate branch and make a Pull Request.
3. After review and acceptance, the PR is merged and closed.
4. You are automatically listed as a contributor on the repo and the project's site (to follow)

Make sure the maven build

``$ mvn clean install -Pcheckstyle``

**passes before making a PR**. 
