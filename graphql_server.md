# GraphQL Server

In this section we are going to setup a GraphQL server using Spring Boot. Before we get started please read about 
[GraphQL Execution](https://graphql.github.io/learn/execution/) so that you have a grasp on the terminology we are going
to use.

## Setup
* Import the [graphql-java-server/](graphql-java-server) folder into your favorite IDE.
  * This project uses lombok. For information on how to add this to your IDE, go to [their website](https://projectlombok.org/).
  * If you are using intellij go to File &rarr; New &rarr; Project From Existing Sources &rarr; Import as a Gradle project
* Run the application. You can do this by going to the GraphQLApplication class and selecting run as java application or by
running `./gradlew bootRun` or by using whatever tooling your IDE provides for this sort of thing. If it doesn't start, then
something horribly wrong has happened.

## Quick Tour
You are obsessed with gardening but you can never remember how many and what type of plants you have in your garden. Like
any good programmer, you decide the best way to deal with this problem is to write a little garden inventory application.

###### What is already done for you
* `Garden` and `Plant` JPA entities. Garden has many Plants. 
  * Repositories and CRUD services for those entities are also done
  * `hsqldb` is setup as the database.
* GraphQL Dependencies
  * `com.graphql-java:graphql-spring-boot-starter`
    * This handles most of the configuration needed to get a GraphQL endpoint up and running.
  * `com.graphql-java:graphql-java-tools`
    * A schema-first tool for graphql-java inspired by graphql-tools for Javascript
    * There a number of other [GraphQL Libraries](https://github.com/graphql-java/awesome-graphql-java) we could have used. 
This library is very well supported and so things don't get too confusing that is all we are going to use.
  * `com.graphql-java:graphiql-spring-boot-starter`
    * A graphical interactive in-browser GraphQL IDE that we can use to query our Garden application.
    
## Exercise #1 - Schema


