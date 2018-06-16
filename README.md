# graphql-devday #

Welcome to the GraphQL development day. Please start off by reading [Introduction to GraphQL](https://graphql.org/learn/)
to get an overview of what GraphQL is.

## Queries and Mutations ##

### Setup ###
To learn about GraphQL we are going to use the GitHub GraphQL endpoint. To set this up go to the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) 
and click **Sign in with GitHub**. You will need to authorize GraphQL API Explorer to access your git repos. If you aren't
comfortable with that, I would suggest pairing with one of your less cowardly coworkers. 

To confirm the setup worked, run the sample query by hitting the play button. The result should return your login id.
 
### Querying ###

#### Exercise #1 - Fields ####

###### Prerequisites ######
* Read [Queries &rarr; Fields](https://graphql.org/learn/queries/#fields)

###### Tasks ######
You are bored and want to look up information about your GitHub user, but you are too proud to go to the GitHub website to view it.
Create a query using the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) to get back the following information about your user:
* login user name
* name
* avatar url
* url
* bio
* createdAt
* updatedAt

<details>
    <summary>Hint #1</summary>
    <p>
        Use the `viewer` root object
    </p>
</details>

<details>
    <summary>Hint #2</summary>
    <p>
        The start of the query should look something like this:
        ```graphql
        query { 
          viewer {
            login
          }
        }
        ```
    </p>
</details>

<details>
    <summary>Answer</summary>
    <p>
        __Query__
        ```graphql
        query { 
          viewer {
            login
            name
            avatarUrl
            url
            bio
            createdAt
            updatedAt
          }
        }
        ```
        
        __Response__
        ```graphql
        {
          "data": {
            "viewer": {
              "login": "youruser",
              "name": "your name",
              "avatarUrl": "https://avatars1.githubusercontent.com/u/1234",
              "url": "https://github.com/youruser",
              "bio": null,
              "createdAt": "2012-05-04T01:05:26Z",
              "updatedAt": "2018-05-03T16:44:05Z"
            }
          }
        }
        ```
    </p>
</details>

## Making a GraphQL Server ##

TODO: Lex

## Making a Angular GraphQL Client ##

TODO: Probably Jim
