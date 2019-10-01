USE CSCE314-908-T4_MovieDB;

/* */
SELECT "primaryTitle" from "titleBasics";

/* Connecting names, their job and the titles they worked on */
SELECT "primaryName", "primaryTitle", "job"
FROM "nameBasics", "titleBasics", "titlePrincipal" 
INNER JOIN "titlePrincipal" ON "titlePrincipal".tconst = "titleBasics".tconst
INNER JOIN "nameBasics" ON "titlePrincipal".nconst = "nameBasics".nconst
WHERE "job" = "self";

/* Getting every movie Brad Pitt was involved in*/
SELECT "primaryName", "primaryTitle", "job"
FROM "nameBasics", ""titleBasics"", "titlePrincipal"
INNER JOIN nameBasics ON nameBasics.nconst = titlePrincipal.nconst
INNER JOIN "titleBasics" ON titlePrincipal.tconst = "titleBasics".tconst
WHERE "primaryName" = "Brad Pitt";

/* Getting Leonardo DiCaprio's known for titles*/
SELECT "primaryName", "primaryTitle"
FROM "nameBasics", "titleBasics"
INNER JOIN "titleBasics" ON "titleBasics".tconst = "KnownFor".tconst
INNER JOIN "nameBasics" ON "nameBasics".nconst = "KnownFor".nconst
WHERE "primaryName" = "Leonardo DiCaprio";

SELECT Count(*) FROM "titleBasics";

/* Getting the highest rated title on IMDB*/
SELECT MAX("averageRating"), "primaryTitle"
FROM "titleRatings", "titleBasics"
INNER JOIN "titleRatings" ON "titleRatings".tconst = "titleBasics".tconst;

/*Counting the number of distinct genre's in IMDB*/
SELECT Count(DISTINCT "genre") FROM "Genre";

/*Counting the number of Adult Films*/
SELECT Count("tconst") FROM "titleBasics"
WHERE "isAdult" = '0';

SELECT Count("averageRating") FROM "titleRatings"
Where "averageRating" < 2;

/*Counting the number of Movies Margot robbie is in*/
SELECT Count("tconst"), "primaryName"
FROM "titlePrincipal", "nameBasics"
INNER JOIN "titlePrincipal" ON "titlePrincipal.nconst" = "nameBasics.nconst"
WHERE "primaryName" = "Margot Robbie";

--Counting the number of excellent movies on IMDB
SELECT Count("averageRating") FROM "titleRatings"
WHERE "averageRating" >9;

--Finding a perfect movie on IMDB
SELECT Count("averageRating") FROM "titleRatings"
WHERE "averageRating" = 10;

--Counting Number of Distinct actresses
SELECT Count(DISTINCT "nconst") FROM "titlePrincipal"
Where "job" = "actress";

--Counting number of distinct actors
SELECT Count(DISTINCT "nconst") FROM "titlePrincipal"
Where "job" = "actor";

SELECT Count("averageRating") 
From "titleRatings" 
WHERE "averageRating" = 1 AND "numVotes" > 10000;

SELECT Count("averageRating") 
From "titleRatings" 
WHERE "averageRating" > 9 AND "numVotes" > 10000;

--
SELECT "primaryTitle"
From "titleBasics"
INNER JOIN "titleRatings" ON "titleBasics".tconst = "titleRatings".tconst
Where "titleRatings".averageRating = 1 AND "titleRatings".numVotes >10000;

--Finding Ozymandias
SELECT "primaryTitle"
From "titleBasics"
INNER JOIN "titleRatings" ON "titleBasics".tconst = "titleRatings".tconst
Where "titleRatings"."averageRating" = 10 AND "titleRatings"."numVotes" >10000;

SELECT Count(DISTINCT "nconst") FROM "titlePrincipal"
Where "job" = "director";

SELECT Count(DISTINCT "nconst") FROM "titlePrincipal"
Where "job" = "writer";

SELECT Count(DISTINCT "nconst") FROM "titlePrincipal"
Where "job" = "cinematographer";

SELECT MIN(DISTINCT "birthYear") FROM "nameBasics";