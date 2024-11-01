select MEMBER_ID,MEMBER_NAME,GENDER,subString(DATE(DATE_OF_BIRTH),1,10) as DATE_OF_BIRTH
from member_profile
where substring(date(date_of_birth),7,1) = 3
and TLNO is not null
and GENDER = 'W'
order by member_id asc