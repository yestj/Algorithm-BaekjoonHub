# -- 코드를 입력하세요

# SELECT J.HISTORY_ID,
#        ROUND(J.DAILY_FEE * J.DURATION * (100 - IFNULL(D.DISCOUNT_RATE, 0)) / 100) AS FEE
# FROM (SELECT C.CAR_TYPE, C.DAILY_FEE, H.HISTORY_ID, DATEDIFF(H.END_DATE, H.START_DATE) + 1 AS DURATION,
#         CASE
#             WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 90 THEN '90일 이상'
#             WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 30 THEN '30일 이상'
#             WHEN DATEDIFF(H.END_DATE, H.START_DATE) + 1 >= 70 THEN '7일 이상'
#             ELSE '할인 없음' END AS DURATION_TYPE
#       FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
#       INNER JOIN CAR_RENTAL_COMPANY_CAR C
#       ON C.CAR_ID = H.CAR_ID
#       WHERE C.CAR_TYPE = '트럭') J
# LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN D
#     ON J.CAR_TYPE = D.CAR_TYPE
#         AND J.DURATION_TYPE = D.DURATION_TYPE
# ORDER BY FEE DESC, J.HISTORY_ID DESC 

WITH value AS (
    SELECT car.daily_fee, car.car_type, his.history_id,
           DATEDIFF(end_date, start_date) + 1 AS period,
    CASE 
      WHEN DATEDIFF(end_date, start_date) + 1 >= 90 THEN '90일 이상'
      WHEN DATEDIFF(end_date, start_date) + 1 >= 30 THEN '30일 이상'
      WHEN DATEDIFF(end_date, start_date) + 1 >= 7 THEN '7일 이상'
      ELSE 'NONE' END AS duration_type
FROM car_rental_company_rental_history AS his
INNER JOIN car_rental_company_car AS car ON car.car_id = his.car_id
WHERE car.car_type = '트럭')   



SELECT value.history_id, 
    ROUND(value.daily_fee * value.period * 
          (100 - IFNULL(plan.discount_rate,0)) / 100) AS FEE
FROM value
LEFT JOIN car_rental_company_discount_plan AS plan 
    ON plan.duration_type = value.duration_type 
    AND plan.car_type = value.car_type
ORDER BY 2 DESC, 1 DESC
