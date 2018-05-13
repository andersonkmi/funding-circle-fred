# Funding Circle Data Engineering Coding Quiz

## Introduction 
For this exercise, we would like you to build a simple application for pulling and storing different kinds of
macroeconomic data using the Federal Reserve’s FRED API. The FRED API provides a RESTful means of
accessing many datasets published by the Federal Reserve – unemployment rates, interest rates, GDP etc.

The data are modelled as a ‘series’ of ‘observations’ with each observation having a date and value. For more
details on the API and datasets, visit https://research.stlouisfed.org/docs/api/fred/ The endpoint you will be
most interested in is fred/series/observations, so you may want to read the docs for that one in particular.

You’ll need to create a FRED user account and API key for yourself, which you can do here:
https://research.stlouisfed.org/useraccount/register/step1 Once you have an account, request your API key and
use it in your requests.

## Requirements

1. Your application should fetch the following FRED series in their entirety:
    * Real Gross Domestic Product (GDPC1)
    * University of Michigan Customer Sentiment Index (UMCSENT)
    * US Civilian Unemployment Rate (UNRATE)

2. Your application should store the observations in a relational database running on a localhost.

3. Your application should allow for two data flows: initial and incremental. The initial data flow should
   clear up the data persisted in the database (if any) and load it afresh. The incremental data flow should
   only load the data that is not present in the database yet.
   
4. Please answer the following question using the observations stored in your database:
   What was the average rate of unemployment for each year starting in 1980 and going up to 2015?
   
## Technical requirements

In order to build and run this coding quiz, there are some requirements that must be met. They are
described as follows.
