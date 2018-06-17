# Queries and Mutations

## Setup
To learn about GraphQL we are going to use the GitHub GraphQL endpoint. To set this up go to the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) 
and click **Sign in with GitHub**. You will need to authorize GraphQL API Explorer to access your git repos. If you aren't
comfortable with that, I would suggest pairing with one of your less cowardly coworkers. 

To confirm the setup worked, run the sample query by hitting the play button. The result should return your login id.

## Exercise #1 - Fields

#### Prerequisites
* Read [Queries and Mutations &rarr; Fields](https://graphql.org/learn/queries/#fields)

#### Tasks
You are bored and want to look up information about your GitHub user, but you are too proud to go to the GitHub website to view it.
Create a query using the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) to get back the following information about your user:
* login user name
* name
* avatar url
* url
* bio
* createdAt
* updatedAt

Make sure you take advantage of the auto complete feature (ctrl + space) and the documentation located on the right side of the explorer.

<details><summary>Hint [Where do I even start?]</summary><p>

Use the `viewer` root object

</p></details>
<details><summary>Hint [I'm still lost. Get me started!]</summary><p>

The start of the query should look something like this:
```graphql
query { 
  viewer {
    login
  }
}
```

</p></details>
<details><summary>Answer</summary><p>

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

</p></details>

## Exercise #2 - Arguments

#### Prerequisites
* Read [Queries and Mutations &rarr; Arguments](https://graphql.org/learn/queries/#arguments)

#### Tasks
Not being able to do translations in typescript files in Angular has been driving you crazy. You recently found the issue
that is going to fix this problem on the Angular GitHub repository and you want to check the status of the issue on GitHub
but you are too lazy to go to the website to check it.

Create a query using the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) to get back the following information from the Angular GitHub repository:
* Repository title and name
* Translation issue title, url and state


The owner of the Angular repository is **angular** and the name of the repository is **angular** as well. The issue number is **11405**.

<details><summary>Hint [Where do I even start?]</summary><p>

Use the `repository` root object

</p></details>
<details><summary>Hint [I'm still lost. Get me started!]</summary><p>

The start of the query should look like this:
```graphql
query {
  repository(owner: "angular", name: "angular") {  
  }
}
```

</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
query {
  repository(owner: "angular", name: "angular") {
    name
    description
    issue(number: 11405) {
      title
      url
      state
    }
  }
}
```

__Response__
```graphql
{
  "data": {
    "repository": {
      "name": "angular",
      "description": "One framework. Mobile & desktop.",
      "issue": {
        "title": "i18n: Able to use translation strings outside a template",
        "url": "https://github.com/angular/angular/issues/11405",
        "state": "OPEN"
      }
    }
  }
}
```

</p></details>

## Exercise #3 - Aliases

#### Prerequisites
* Read [Queries and Mutations &rarr; Aliases](https://graphql.org/learn/queries/#aliases)

#### Tasks
You discover that you love Bazel, but there is an issue in Angular preventing you from using it. You want to track
the Bazel issue as well as the translation issue from **Exercise 2**. The only problem is that it is confusing which issue
is which in your query. Luckily, you just read about GraphQL Aliases. 
 
Using the query from **Exercise 2** add the bazel issue (issue number 24521) with the same fields as the other issue (title, url and state).

Replace the following fields with aliases:
* repository &rarr; angularRepository
* issue(number: 11405) &rarr; translationIssue
* issue(number: 24521) &rarr; bazelIssue
* issue state &rarr; status

<details><summary>Hint [Exercise 2 Answer]</summary><p>

The answer to **Exercise 2** is:
```graphql
query {
  repository(owner: "angular", name: "angular") {
    name
    description
    issue(number: 11405) {
      title
      url
      state
    }
  }
}
```

</p></details>
<details><summary>Hint [I can't figure out how to replace a field with an alias]</summary><p>

Renaming the repository looks like this: 
```graphql
query {
  angularRepository: repository(owner: "angular", name: "angular") {
    name
    ...
  }
}
```

</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
query {
  angularRepository: repository(owner: "angular", name: "angular") {
    name
    description
    translationIssue: issue(number: 11405) {
      title
      url
      status: state
    }
    bazelIssue: issue(number: 24521) {
      title
      url
      status: state
    }
  }
}
```

__Response__
```graphql
{
  "data": {
    "angularRepository": {
      "name": "angular",
      "description": "One framework. Mobile & desktop.",
      "translationIssue": {
        "title": "i18n: Able to use translation strings outside a template",
        "url": "https://github.com/angular/angular/issues/11405",
        "status": "OPEN"
      },
      "bazelIssue": {
        "title": "Bazel build of router broken in 6.0.5",
        "url": "https://github.com/angular/angular/issues/24521",
        "status": "OPEN"
      }
    }
  }
}
```

</p></details>

## Exercise #4 - Fragments

#### Prerequisites
* Read [Queries and Mutations &rarr; Fragments](https://graphql.org/learn/queries/#fragments)

#### Tasks
Now that you've read about fragments, you realize that the query from **Exercise 3** could be improved by adding a fragment
for the two issues.

Write a fragment to replace the repeated title, url and status fields on the issue object.

<details><summary>Hint [Exercise 3 Answer]</summary><p>

The answer to **Exercise 3** is:
```graphql
query {
  angularRepository: repository(owner: "angular", name: "angular") {
    name
    description
    translationIssue: issue(number: 11405) {
      title
      url
      status: state
    }
    bazelIssue: issue(number: 24521) {
      title
      url
      status: state
    }
  }
}
```

</p></details>
<details><summary>Hint [How is this stupid fragment thing supposed to look anyway?]</summary><p>

The fragment should look like this:
```graphql
fragment issueFields on Issue {
      title
      url
      status: state
}
```

</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
query {
  angularRepository: repository(owner: "angular", name: "angular") {
    name
    description
    translationIssue: issue(number: 11405) {
      ...issueFields
    }
    bazelIssue: issue(number: 24521) {
      ...issueFields
    }
  }
}

fragment issueFields on Issue {
      title
      url
      status: state
}
```

__Response__
```graphql
{
  "data": {
    "angularRepository": {
      "name": "angular",
      "description": "One framework. Mobile & desktop.",
      "translationIssue": {
        "title": "i18n: Able to use translation strings outside a template",
        "url": "https://github.com/angular/angular/issues/11405",
        "status": "OPEN"
      },
      "bazelIssue": {
        "title": "Bazel build of router broken in 6.0.5",
        "url": "https://github.com/angular/angular/issues/24521",
        "status": "OPEN"
      }
    }
  }
}
```

</p></details>

## Exercise #5 - Operation name

#### Prerequisites
* Read [Queries and Mutations &rarr; Operation name](https://graphql.org/learn/queries/#operation-name)

#### Tasks

You are extremely proud of the query from **Exercise #5** you made. Give it a good operation name.

<details><summary>Answer</summary><p>

__Query__
```graphql
query IssueWatcher {
  angularRepository: repository(owner: "angular", name: "angular") {
    name
    description
    translationIssue: issue(number: 11405) {
      ...issueFields
    }
    bazelIssue: issue(number: 24521) {
      ...issueFields
    }
  }
}

fragment issueFields on Issue {
      title
      url
      status: state
}
```

</p></details>

## Exercise #6 - Variables

#### Prerequisites
* Read [Schemas and Types &rarr; Type system](https://graphql.org/learn/schema/#type-system)
* Read [Queries and Mutations &rarr; Variables](https://graphql.org/learn/queries/#variables)

#### Tasks
You just read about variables and you just thought of the perfect use case for it. You want to write a query using variables
to look up the name, url, and bio of any GitHub user. Wow that is such a cool idea.

Write a query that accepts a login as a required variable and returns the name, url, and bio of the GitHub user.

<details><summary>Hint [What root object am I supposed to start with?]</summary><p>

Use the `user` root object

</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
query UserLookup($login: String!) {
  user(login: $login) {
    name
    bio
    url
  }
}
```

__Query Variables__
```graphql
{
  "login": "jimeh87"
}
```

__Response__
```graphql
{
  "data": {
    "user": {
      "name": "Jim",
      "bio": null,
      "url": "https://github.com/Jimeh87"
    }
  }
}
```

</p></details>

## Exercise #7 - Directives and Pagination

#### Prerequisites
* Read [Queries and Mutations &rarr; Directives](https://graphql.org/learn/queries/#variables)
* Read [Best Practices &rarr; Pagination](https://graphql.org/learn/pagination/)

#### Tasks
An AMAZING enhancement to the query you made in **Exercise #6** would be if you could view the repositories that
user has. You only want the repositories sometimes though. Add an optional parameter to your user look up query that is defaulted
to false that returns the last 10 user repositories name and description if true.

<details><summary>Hint [Exercise 6 Answer]</summary><p>

The answer to **Exercise 6** is:

__Query__
```graphql
query UserLookup($login: String!) {
  user(login: $login) {
    name
    bio
    url
  }
}
```

__Query Variables__
```graphql
{
  "login": "jimeh87"
}
```

</p></details>

<details><summary>Hint [How does this connection edge node mumbo jumbo work?]</summary><p>

If you are struggling with what an edge and a node is, this is how the GitHub documentation describe them:
> #### Connection
>Connections let you query related objects as part of the same call. With connections, you can use a single GraphQL call where you would have to use multiple calls to a REST API. For more information, see "Migrating from REST to GraphQL."
>
>It's helpful to picture a graph: dots connected by lines. The dots are nodes, the lines are edges. A connection defines a relationship between nodes.
>
> ##### Edge
>Edges represent connections between nodes. When you query a connection, you traverse its edges to get to its nodes. Every edges field has a node field and a cursor field. Cursors are used for pagination.
>
> ##### Node
>Node is a generic term for an object. You can look up a node directly, or you can access related nodes via a connection. If you specify a node that does not return a scalar, you must include subfields until all fields return scalars. For information on accessing node IDs via the REST API v3 and using them in GraphQL queries, see "Using Global Node IDs."

</p></details>
<details><summary>Hint [I'm still lost. Show me the light]</summary><p>

The repositories query section should look something like this:
```graphql
repositories(last: 10) @include(if: $withRepositories) {
  edges {
    node {
      name,
      description
    }
  }
}
```
  
</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
query userLookup($login: String!, $withRepositories: Boolean = false) {
  user(login: $login) {
    name
    bio
    url
    repositories(last: 10) @include(if: $withRepositories) {
      edges {
        node {
          name,
          description
        }
      }
    }
  }
}
```

__Query Variables__
```graphql
{
  "login": "jimeh87",
  "withRepositories": true
}
```

__Response__
```graphql
{
  "data": {
    "user": {
      "name": "Jim",
      "bio": null,
      "url": "https://github.com/Jimeh87",
      "repositories": {
        "edges": [
          {
            "node": {
              "name": "training",
              "description": null
            }
          },
          {
            "node": {
              "name": "game-of-life",
              "description": "Angular 4 / Bootstrap 4 / Canvas implementation of the game of life"
            }
          },
          {
            "node": {
              "name": "angular-attack-2018",
              "description": "The 2018 Angular Attack Entry by Lowered Expectations"
            }
          },
          {
            "node": {
              "name": "rankit",
              "description": null
            }
          },
          {
            "node": {
              "name": "graphql-devday",
              "description": null
            }
          }
        ]
      }
    }
  }
}
```

</p></details>

## Exercise #8 - Mutations
#### Prerequisites
* Read [Queries and Mutations &rarr; Mutations](https://graphql.org/learn/queries/#mutations)
#### Tasks
You are starting to become obsessed with the GitHub GraphQL API and refuse to use the website. The only problem is that
there is an issue you want to add a reaction to, and you only just started learning about mutations. 

Write a mutation to add a reaction to issue **number 1** on repository **graphql-devday** with an owner of **jimeh87**

In order to add a reaction to the issue, you are going to need the **issue id**. You can write a query yourself, or you
can take a peek at the first hint. Don't forget about the documentation to help figure out what to pass the mutation. Auto-complete
will not be very helpful.

Once you have run the mutation, you should be able to see your reaction by going to [Issue #1](https://github.com/Jimeh87/graphql-devday/issues/1).

<details><summary>Hint [Get me the gosh darn issue id query]</summary><p>

```graphql
query {
  repository(owner: "Jimeh87", name: "graphql-devday") {
    issue(number: 1) {
      id
      title
      reactions(last: 100) {
        edges {
          node {
            user {
              login
              name
            }
            content
          }
        }
      }
    }
  }
}
```
  
</p></details>
<details><summary>Hint [What mutation am I supposed to be using]</summary><p>

The `addReaction` mutation is what you are looking for.
  
</p></details>
<details><summary>Hint [I can't figure out what to put in the input parameter]</summary><p>

The subjectId is the id of the issue from the query in the first hint. The content is of ReactionContent type which can
be `THUMBS_UP`, `THUMBS_DOWN`, `LAUGH`, `HOORAY`, `CONFUSED`, or `HEART`

```graphql
input: {
    subjectId: "put id from query here"
    content: THE_REACTION_CONTENT
}
```
  
</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
mutation {
  addReaction(input: {
    subjectId: "MDU6SXNzdWUzMzMwMzY1NTk="
    content: CONFUSED
  }) {
    reaction {
      id
      user {
        login
        name
      },
      content
    }
  }
}
```

__Response__
```graphql
{
  "data": {
    "addReaction": {
      "reaction": {
        "id": "MDg6UmVhY3Rpb24yNTE5MjkyNw==",
        "user": {
          "login": "Jimeh87",
          "name": "Jim"
        },
        "content": "CONFUSED"
      }
    }
  }
}
```

</p></details>

## Exercise #9 - Inline Fragments and Meta Fields

#### Prerequisites
* Read [Queries and Mutations &rarr; Inline Fragments](https://graphql.org/learn/queries/#inline-fragments)
* Read [Queries and Mutations &rarr; Meta Fields](https://graphql.org/learn/queries/#meta-fields)
#### Tasks
You just watched a youtube video on how to become a l33t hacker and you want to start by hacking the github. First you need a
bunch of logins to try your new skillz.

Write a query to figure out the user login of a repository owner(s). Note that a `Repository` can be owned by a `User` which has one user or an
`Organization` which has many users. So in the case of an organization, I would expect many results returned. I would also like to know the
type of owner (user or organization)

For test data you can use `owner:"angular", name: "angular"` for an organization and `owner:"basarat", name: "typescript-collections"` for a
single user owner or any other repositories you can think of.

<details><summary>Hint [What root object am I supposed to use?]</summary><p>

Use the `repository` root object

</p></details>
<details><summary>Hint [The multiple implementations of Owner is confusing me. Help a brother / sister out]</summary><p>

Since RepositoryOwner can implement user or organization, you will need to write specific code for each type. Something like this should get you started:
```graphql
query {
  repository(owner: "foo", name: "bar") {
    owner {
      ... on User {
        login
      }
      ... on Organization {
        Find all users in organization's logins
      }
    }
  }
}

```

</p></details>
<details><summary>Answer</summary><p>

__Query__
```graphql
query findOwnerLogins($owner: String!, $name: String!) {
  repository(owner: $owner, name: $name) {
    owner {
      __typename
      ... on User {
        login
      }
      ... on Organization {
        members(first: 10) {
          edges {
            node {
              login
            }
          }
        }
      }
    }
  }
}

```

__Query Variables (User)__
```graphql
{
  "owner": "basarat",
  "name": "typescript-collections"
}
```

__Response (User)__
```graphql
{
  "data": {
    "repository": {
      "owner": {
        "__typename": "User",
        "login": "basarat"
      }
    }
  }
}
```

__Query Variables (Organization)__
```graphql
{
  "owner": "angular",
  "name": "angular"
}
```

__Response (Organization)__
```graphql
{
  "data": {
    "repository": {
      "owner": {
        "__typename": "Organization",
        "members": {
          "edges": [
            {
              "node": {
                "login": "heathkit"
              }
            },
            {
              "node": {
                "login": "petebacondarwin"
              }
            },
            {
              "node": {
                "login": "shyndman"
              }
            },
            {
              "node": {
                "login": "jamesdaniels"
              }
            },
            {
              "node": {
                "login": "markovuksanovic"
              }
            },
            {
              "node": {
                "login": "matsko"
              }
            },
            {
              "node": {
                "login": "linclark"
              }
            },
            {
              "node": {
                "login": "mhevery"
              }
            },
            {
              "node": {
                "login": "StephenFluin"
              }
            },
            {
              "node": {
                "login": "IgorMinar"
              }
            }
          ]
        }
      }
    }
  }
}
```

</p></details>

## Exercise #10 - Introspection

#### Prerequisites
* Read [Introspection](https://graphql.org/learn/introspection/)
* Read [GitHub Introspection](https://developer.github.com/v4/guides/intro-to-graphql/#discovering-the-graphql-api)
#### Tasks
You just read two articles on GraphQL introspection and have ascended above documentation. You no longer read documentation, 
you query it. Write a query using introspection to figure out the two possible types that a `RepositoryOwner` can be. 

<details><summary>Answer</summary><p>

__Query__
```graphql
query {
  __type(name: "RepositoryOwner") {
    name
    possibleTypes {
      name
    }
  }
}
```

__Response__
```graphql
{
  "data": {
    "__type": {
      "name": "RepositoryOwner",
      "possibleTypes": [
        {
          "name": "Organization"
        },
        {
          "name": "User"
        }
      ]
    }
  }
}
```

</p></details>