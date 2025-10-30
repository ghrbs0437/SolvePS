# -- 코드를 작성해주세요

# WITH 
# TB1 AS (
#     SELECT B.ID
#     FROM ECOLI_DATA A 
#         INNER JOIN ECOLI_DATA B
#         ON A.ID = B.PARENT_ID
#     WHERE A.PARENT_ID IS NOT NULL
# )
# ,
# TB2 AS (
#     SELECT C.ID
#     FROM ECOLI_DATA A 
#         INNER JOIN ECOLI_DATA B
#         ON A.ID = B.PARENT_ID
#         INNER JOIN ECOLI_DATA C
#         ON B.ID = C.PARENT_ID
#     WHERE A.PARENT_ID IS NOT NULL
# )


# SELECT a.id
# FROM TB1 A 
# left outer join TB2 B
# on a.id = b.id
# where b.id is null

SELECT c.ID
FROM ECOLI_DATA AS c
JOIN ECOLI_DATA AS p1 ON c.PARENT_ID = p1.ID
JOIN ECOLI_DATA AS p0 ON p1.PARENT_ID = p0.ID
WHERE p0.PARENT_ID IS NULL
ORDER BY c.ID;