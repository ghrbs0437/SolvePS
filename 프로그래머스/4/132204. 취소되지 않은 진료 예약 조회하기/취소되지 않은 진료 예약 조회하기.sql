-- 코드를 입력하세요

-- 2022년 4월 13일 취소되지 않은 흉부외과(CS) 진료 예약 내역을 조회하는 SQL문을 작성해주세요. 
-- 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시 항목이 출력되도록 작성해주세요. 결과는 진료예약일시를 기준으로 오름차순 정렬해주세요.


SELECT 
C.APNT_NO, A.PT_NAME, C.PT_NO, C.MCDP_CD
, B.DR_NAME, C.APNT_YMD
FROM PATIENT A, DOCTOR B, APPOINTMENT C
WHERE
C.APNT_CNCL_YN = 'N' AND
DATE(C.APNT_YMD) = '2022-04-13' AND
C.MCDP_CD = 'CS' AND
C.PT_NO = A.PT_NO AND
C.MDDR_ID = B.DR_ID
ORDER BY C.APNT_YMD ASC


# SELECT 
# *
# FROM APPOINTMENT C
# WHERE
# C.APNT_CNCL_YN = 'N' AND
# DATE(C.APNT_YMD) = '2022-04-13' AND
# C.MCDP_CD = 'CS'