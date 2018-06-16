# GraphQL Dev Day #

Welcome to the GraphQL development day. Please start off by reading [Introduction to GraphQL](https://graphql.org/learn/)
to get an overview of what GraphQL is.

# Queries and Mutations #

## Setup ##
To learn about GraphQL we are going to use the GitHub GraphQL endpoint. To set this up go to the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) 
and click **Sign in with GitHub**. You will need to authorize GraphQL API Explorer to access your git repos. If you aren't
comfortable with that, I would suggest pairing with one of your less cowardly coworkers. 

To confirm the setup worked, run the sample query by hitting the play button. The result should return your login id.
 
## Querying ##

### Exercise #1 - Fields ###

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

Make sure you take advantage of the auto complete feature (ctrl + space) and the documentation located on the right side of the explorer.

<details><summary>Hint #1</summary><p>

Use the `viewer` root object

</p></details>
<details><summary>Hint #2</summary><p>

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

### Exercise #2 - Arguments ###

###### Prerequisites ######
* Read [Queries &rarr; Arguments](https://graphql.org/learn/queries/#arguments)

###### Tasks ######
Not being able to do translations in typescript files in Angular has been driving you crazy. You recently found the issue
that is going to fix this problem on the Angular GitHub repository and you want to check the status of the issue on GitHub
but you are too lazy to go to the website to check it.

Create a query using the [GitHub GraphQL API explorer](https://developer.github.com/v4/explorer/) to get back the following information from the Angular GitHub repository:
* Repository title and name
* Translation issue title, url and state


The owner of the Angular repository is **angular** and the name of the repository is **angular** as well. The issue number is **11405**.

<details><summary>Hint #1</summary><p>

Use the `repository` root object

</p></details>
<details><summary>Hint #2</summary><p>

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

### Exercise #3 - Aliases ###

###### Prerequisites ######
* Read [Queries &rarr; Aliases](https://graphql.org/learn/queries/#aliases)

###### Tasks ######
You discover that you love Bazel, but there is an issue on the Angular site preventing you from using it. You want to track
the Bazel issue as well as the translation issue from **Exercise 2**. The only problem is that it is confusing which issue
is which in your query. Luckily, you just read about GraphQL Aliases. 
 
Using the query from **Exercise 2** add the bazel issue (issue number 24521)

Replace the following fields with aliases:
* repository &rarr; angularRepository
* issue(number: 11405) &rarr; translationIssue
* issue(number: 24521) &rarr; bazelIssue
* issue state &rarr; status

<details><summary>Hint #1</summary><p>

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
<details><summary>Hint #2</summary><p>

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



## Making a GraphQL Server ##

TODO: Lex

## Making a Angular GraphQL Client ##

TODO: Probably Jim
