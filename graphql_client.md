# GraphQL Angular Client

In this section we are going to integrate with the GraphQL endpoint we just created in **section 2**. We will be using 
[the Apollo client](https://www.apollographql.com) and Angular to help us accomplish this.

## Setup
* Import the [graphql-angular/](graphql-angular) folder into your favorite front-end development4 IDE.
* After it is imported run `yarn` from the [graphql-angular/](graphql-angular) directory.
* To start the application run `yarn start`
* Start your server from **section 2**
  * If you didn't complete **section 2** you can use the answer server instead. 
## Quick Tour
We are going to be building a client for the server that we just built in **section 2**. This project uses Angular 6 and
has `bootstrap` and `ng-bootstrap` installed. You will be primarily working with the services in the project. Everything
else should be completed for you already unless you are working on more advanced questions. If you find yourself stuck and 
check out the [graphql-angular-answer](graphql-angular-answer) directory for a complete version of the client.    

## Exercise #1 - My First Angular GraphQL Query

#### Tasks

* Use [this setup guide](https://www.apollographql.com/docs/angular/basics/setup.html) to setup the GraphQL client in our
Angular project
  * Since we are using yarn, run the following to install all of the packages
    ```
    yarn add apollo-angular apollo-angular-link-http apollo-link apollo-client apollo-cache-inmemory graphql-tag graphql
    ```
* Read the [Basic Queries](https://www.apollographql.com/docs/angular/basics/queries.html#basics) section to understand
how to use the Apollo client.
* In the `GardenService` replace the contents of the `getGardens()` method with a query to get all the gardens. Use the 
`watchQuery` method instead of the query method.
  * The query should return an Observable of type Garden with all of the fields in the Garden filled in.
  
#### Test it out
To test it out you should be able to hit [http://localhost:4200](http://localhost:4200) and see garden data populated in
the tables.

## Exercise #2 - ADD ALL THE PLANTS!
![Add all the meme](X-All-The-Y.jpg)
* Read the [Mutations](https://www.apollographql.com/docs/angular/basics/mutations.html) section to understand how to use
mutations with the Apollo client.
* Write a mutation in the `PlantService#createPlant` function that creates a plant and returns the plant to the caller as
an Observable.

#### Test it out
To test it out you should be able to hit [http://localhost:4200](http://localhost:4200) and add a plant. The plant
will not be added to the garden list, so use [http://localhost:8080/graphiql](your graphiql) to see if the plant was added. 

## Exercise #3 - CACHE ALL THE THINGS!
![Add all the meme](X-All-The-Y.jpg)
Apollo uses an in memory cache to store the results from all of the queries that get run. The idea is that you can then update
the cache when you run a mutation. 

The goal of this exercise is to update the getGardens() query result cache without rerunning any queries
* Read the [Apollo Cache](https://www.apollographql.com/docs/angular/basics/caching.html) section and the [Direct cache access](https://www.apollographql.com/docs/angular/features/caching.html)
section to understand what you are doing.
* In the `PlantService#createPlant` function add an update function to the mutation to update the cache of the `getGardens()` query.

#### Test it out
You should now be able to add a plant in the UI and the plant will be automatically added to the garden. 

## Exercise #4 - INCREMENT ALL THE THINGS!
![Add all the meme](X-All-The-Y.jpg)
There is a plus button next to our plant quantities that should increment the number of plants we have. Write a mutation to 
increment the plant quantity in the `PlantService#incrementPlantQuantity` function.
* Note that you do not need to write an update function in this mutation. I don't have a great understanding of how the Apollo
caching works, it is smart enough to update the cache when changing an entity, but not when adding it.

#### Test it out
Hitting the plus button should increment the number of plants you have.

## Exercise #5 - Mess around
There is a lot more to cover with the Apollo client. For instance check out the [developer tooling](https://www.apollographql.com/docs/angular/features/developer-tooling.html)
and their recipe on [store rehydration](https://www.apollographql.com/docs/angular/recipes/server-side-rendering.html).