# WebGoatReaper: A Coding DOJO based on a deliberately insecure Web Application

# Important

This is a fork of the release version 8.0.0 of WebGoat 8 (https://github.com/WebGoat/WebGoat/archive/release/8.0.0.zip)


# Introduction

WebGoat is a deliberately insecure web application maintained by [OWASP](http://www.owasp.org/) designed to teach web
application security lessons.

This program is a demonstration of common server-side application flaws. The
exercises are intended to be used by people to learn about application security and
penetration testing techniques.

**WARNING 1:** *While running this program your machine will be extremely
vulnerable to attack. You should disconnect from the Internet while using
this program.*  WebGoat's default configuration binds to localhost to minimize
the exposure.

**WARNING 2:** *This program is for educational purposes only. If you attempt
these techniques without authorization, you are very likely to get caught. If
you are caught engaging in unauthorized hacking, most companies will fire you.
Claiming that you were doing security research will not work as that is the
first thing that all hackers claim.*

# Run Instructions:

## 1. Standalone 

Download the latest WebGoatReaper release from [https://github.com/chdutay/WebGoatReaper](https://github.com/chdutay/WebGoatReaper)

```Shell
java -jar webgoat-server-<<version>>.jar
```


## 2. Run from the sources

### Prerequisites:

* Java JDK 8
* Maven > 3.2.1
* Your favorite IDE
* Git, or Git support in your IDE

Open a command shell/window:

```Shell
git clone git@github.com:WebGoatReaper/WebGoatReaper.git
```

Now let's start by compiling the project.

```Shell
cd WebGoatReaper
git checkout 
mvn clean install
```

Now we are ready to run the project. WebGoat 8.x is using Spring-Boot.

```Shell
mvn -pl webgoat-server spring-boot:run
```
... you should be running webgoat on localhost:8080/WebGoat momentarily

To change IP addresss add the following variable to WebGoat/webgoat-container/src/main/resources/application.properties file

```server.address=x.x.x.x
```

