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
else should be completed for you already unless you are working on more advanced questions.

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


