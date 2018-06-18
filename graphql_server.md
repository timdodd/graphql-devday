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
    
## Exercise #1 - The Basics

#### Prerequisites
* Read [GraphQL Schema](https://graphql.org/learn/schema/)

#### Tasks

##### Defining the Schema
Since `graphql-java-tools` is schema first we will be creating a schema, and then implementing the schema as we go.

To create our schema do the following:

1. Create a file in the `src/main/resources` folder called `schema.graphqls`
2. In the `schema.graphqls` add the following types:
    1. plant type with the following fields // TODO Remove this
        * id (required)
        * plantType (required)
        * quantity (required)
    2. garden type with the following fields
        * id (required)
        * title (required)
        * description
        * plants // TODO: Remove this
    3. query type
        * gardens
            * Returns a list of the Garden Type
            
##### Plumbing in the Java

1. Create a Root Query Resolver for gardens and plants
    * Create a class called `QueryResolver` that implements `GraphQLQueryResolver`
    * The class should be annotated with `@Component`
    * The class should have one method
        * `getGardens()` which returns a List of GardenDto's from the `GardenService` (You will need to transform it).
2. 

             
<details><summary>Answer</summary><p>

__schema.graphqls__
```graphql
type Garden {
    id: ID!
    title: String!
    description: String
}

type Query {
    gardens: [Garden]!
}
```

__QueryResolver.java__
```java
package com.jimrennie.graphql.devday.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.service.GardenService;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private GardenService gardenService;

	public List<GardenDto> getGardens() {
		return gardenService.getGardens()
				.stream()
				.map(this::toGardenDto)
				.collect(Collectors.toList());
	}

	private GardenDto toGardenDto(Garden garden) {
		return new GardenDto()
				.setId(garden.getId())
				.setTitle(garden.getTitle())
				.setDescription(garden.getDescription());
	}

}

```

__Query__
```graphql
query {
  gardens {
    id
    title
    description
  }
}
```

__Response__
```json
{
  "data": {
    "gardens": [
      {
        "id": "1",
        "title": "My First Garden",
        "description": "This garden is full of hope, but also full of weeds."
      },
      {
        "id": "5",
        "title": "Herb Garden",
        "description": "Parsley sage rosemary and thyme... and basil and dill"
      }
    ]
  }
}
```

</p></details>

