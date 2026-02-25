SELECT p.ID AS PROJECT_ID,
SUM(w.salary) * DATEDIFF('MONTH', p.start_date, p.finish_date) AS PRICE
FROM project p
JOIN project_worker pw ON p.ID = pw.project_id
JOIN worker w ON pw.worker_id = w.ID
GROUP BY p.ID, p.start_date,  p.finish_date
ORDER BY PRICE DESC;