-- 코드를 작성해주세요

SELECT count(1) as COUNT
FROM ECOLI_DATA
where bin(genotype) like '%01' 
or bin(genotype) like '%100'
or bin(genotype) like '%101'
or bin(genotype) = '1'