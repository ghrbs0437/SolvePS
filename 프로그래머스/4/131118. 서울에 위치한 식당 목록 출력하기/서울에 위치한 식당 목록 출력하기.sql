select aa.rest_id, aa.rest_name, aa.food_type, aa.favorites, aa.address, round(avg(b.review_score),2) as REVIEW_DATE
from 
(
    select a.*
    from rest_info a
    where a.address like '서울%'
) aa inner join rest_review b
on aa.rest_id = b.rest_id
group by aa.rest_id
order by REVIEW_DATE DESC , aa.FAVORITEs DESC