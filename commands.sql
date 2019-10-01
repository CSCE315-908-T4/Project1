USE CSCE314-908-T4_MovieDB;

/* */
SELECT "primaryName" from Title;

/* Connecting names, their job and the titles they worked on */
SELECT "primaryName", "primaryTitle", "job"
FROM "Person", "Title", "Principal" 
INNER JOIN "Principal" ON Principal.tconst = Title.tconst
INNER JOIN "Person" ON Principal.nconst = Person.nconst
WHERE "job" = "self";


/* Getting every movie Brad Pitt was involved in*/

SELECT "primaryName", "primaryTitle", "job"
FROM "Person", "Title", "Principal"
INNER JOIN Person ON Person.nconst = Principal.nconst
INNER JOIN Title ON Principal.tconst = Title.tconst
WHERE "primaryName" = "Brad Pitt";

/* Getting Leonardo DiCaprio's known for titles*/
SELECT "primaryName", "primaryTitle"
FROM "Person", "Title"
INNER JOIN "Title" ON "Title.tconst" = "KnownFor.tconst"
INNER JOIN "Person" ON "Person.nconst" = "KnownFor.nconst"
WHERE "primaryName" = "Leonardo DiCaprio";

SELECT Count(*) FROM "Title";
/* Getting the highest rated title on IMDB*/
SELECT MAX("averageRating"), "primaryTitle"
FROM "Rating", "Title"
INNER JOIN "Rating" ON Rating.tconst = Title.tconst;

/*Counting the number of distinct genre's in IMDB*/
SELECT Count(DISTINCT "genre") FROM "Genre";

/*Counting the number of Adult Films*/
SELECT Count("tconst") FROM "Title"
WHERE "isAdult" = 0;

SELECT Count("averageRating") FROM "Rating"
Where "averageRating" < 2;
/*Counting the number of Movies Margot robbie is in*/
SELECT Count("tconst"), "primaryName"
FROM "Principal", "Person"
INNER JOIN "Principal" ON "Principal.nconst" = "Person.nconst"
WHERE "primaryName" = "Margot Robbie";

--Counting the number of excellent movies on IMDB
SELECT Count("averageRating") FROM "Rating"
WHERE "averageRating" >9;

--Finding a perfect movie on IMDB
SELECT Count("averageRating") FROM "Rating"
WHERE "averageRating" = 10;

--Counting Number of Distinct actresses
SELECT Count(DISTINCT "nconst") FROM "Principal"
Where "job" = "actress";
--Counting number of distinct actors
SELECT Count(DISTINCT "nconst") FROM "Principal"
Where "job" = "actor";

SELECT Count("averageRating") 
From "Ratings" 
WHERE "averageRating" = 1 AND "numVotes" > 10000;

SELECT Count("averageRating") 
From "Ratings" 
WHERE "averageRating" > 9 AND "numVotes" > 10000;

--
SELECT "primaryTitle"
From "Title"
INNER JOIN "Rating" ON "Title.tconst" = "Rating.tconst"
Where "Rating.averageRating" = 1 AND "Rating.numVotes" >10000;

--Finding Ozymandias
SELECT "primaryTitle"
From "Title"
INNER JOIN "Rating" ON "Title.tconst" = "Rating.tconst"
Where "Rating.averageRating" = 10 AND "Rating.numVotes" >10000;

SELECT Count(DISTINCT "nconst") FROM "Principal"
Where "job" = "director";

SELECT Count(DISTINCT "nconst") FROM "Principal"
Where "job" = "writer";

SELECT Count(DISTINCT "nconst") FROM "Principal"
Where "job" = "cinematographer";

