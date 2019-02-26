* Run the application.
* It will start the application on 
  localhost on port 8083.
* Go to localhost:8083/ratingsdata/movieId
* It will return an response like below
```JSON
{"movieId":"movieId","rating":8}
```
* Go to localhost:8083/ratingsdata/users/userName
* It will return an response like below
```json
{"userRating":[{"movieId":"1234","rating":8},{"movieId":"5678","rating":9}]}
```