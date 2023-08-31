
# postcode-api

A Postcode API built in Spring for use in mobile applications. (Based off of \_nology prompt)


## Basic Structures

In this API, there are _two_ entities.

### Postcode

As this API is used to find out information about Australian postcodes, this entity has the following constraints to its fields:

- postcode
  - Integer
  - Must be a four digit integer
- suburbs
  - List<Suburbs>
  - Can be null at initialization

## Features

Within this API, you can perform the following requests:

### Create

#### Creating a Postcode

Send a POST request to the `postcodes` endpoint of your API instance using the following request body:

```json
{
    "postcode": 0000,
    "suburbs": ,
}
```

A new Postcode _doesn't require a List of Suburbs_ to be created, however it can be provided if you wish.

