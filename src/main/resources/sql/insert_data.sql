-- cc_code data 차차 추가할 예정
INSERT INTO
    APPROVAL.CC_CODE (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE)
VALUES
    ('ROLE', 'ROLE_CEO', 1, ''),
    ('ROLE', 'ROLE_ADMIN', 2, ''),
    ('ROLE', 'ROLE_EMP', 3, ''),
    ('ROLE', 'ROLE_USER', 4,  '');

-- dummy data
INSERT INTO
    APPROVAL.CC_DEP (DEPARTMENT_ID, PARENT_DEPARTMENT_ID, DEPARTMENT_NAME, SPARE)
VALUES
    (1, NULL, '경영지원팀', ''),
    (2, 1, '인사팀', ''),
    (3, 1, '재무팀', ''),
    (4, NULL, '개발팀', ''),
    (5, 4, '백엔드팀', ''),
    (6, 4, '프론트엔드팀', ''),
    (7, NULL, '영업팀', ''),
    (8, 7, '국내영업팀', ''),
    (9, 7, '해외영업팀', '');

INSERT INTO APPROVAL.CC_RANK (RANK_ID, PARENT_RANK_ID, RANK_NAME, SPARE) VALUES
-- 최상위 직급
(1, NULL, '회장', ''),
-- 경영 직급
(2, 1, '사장', ''),
(3, 2, '부사장', ''),
(4, 3, '전무', ''),
(5, 4, '상무', ''),
-- 관리 직급
(6, NULL, '이사', ''),
(7, 6, '부장', ''),
(8, 7, '차장', ''),
-- 실무 직급
(9, 8, '과장', ''),
(10, 9, '대리', ''),
(11, 10, '사원', ''),
(12, NULL, '인턴', '');

INSERT INTO APPROVAL.CC_COMPANY (
    BIZ_NO, COMPANY_NAME, COMPANY_ADDRESS, COMPANY_CALL, CREATE_DT,
    UPDATE_DT, OCCUR_LEAVE_DT, REMAIN_LEAVE_WHETHER, DEFAULT_LEAVE_CNT,
    BASIC_WORK_HOURS, OVERTIME_PAY_WHETHER, SPARE
) VALUES (
             '123456789012', 'ABC Corporation', '서울특별시 강남구 테헤란로 123', '02-1234-5678',
             '2020-01-01 00:00:00', NULL, '2020-01-01 00:00:00', 'Y', 15, 40, 'Y', ''
         );

INSERT INTO APPROVAL.CC_EMP (
    EMP_EMAIL, EMP_PHONENUM, EMP_PW, EMP_NAME, EMP_BIRTH,
    EMP_ACCOUNT_STATUS, EMP_POSITION, PW_ERROR_CNT, EMP_JOIN_DT,
    EMP_RETIRE_DT, ROLE_ID, DEPARTMENT_ID, RANK_ID, COMPANY_NO,
    DIRECT_BOSS_ID, SPARE
) VALUES
-- CEO (회장)
('ceo@example.com', '010-1111-2222', 'ceo1234', '홍길동', '1975-06-15 00:00:00',
 'Y', 'CEO', 0, '2010-01-01 09:00:00', NULL, 1, 1, 1, 1, NULL, ''),
-- 관리자 (사장)
('admin@example.com', '010-2222-3333', 'admin1234', '김철수', '1980-01-01 00:00:00',
 'Y', '관리자', 0, '2015-05-01 09:00:00', NULL, 2, 2, 2, 1, 1, ''),
-- 직원 (과장)
('emp@example.com', '010-3333-4444', 'emp1234', '박영희', '1990-03-10 00:00:00',
 'Y', '재무 담당자', 0, '2020-03-15 09:00:00', NULL, 3, 3, 9, 1, 2, ''),
-- 게스트 (사원)
('user@example.com', '010-4444-5555', 'user1234', '이민수', '1995-12-25 00:00:00',
 'Y', '게스트', 0, '2023-01-01 09:00:00', NULL, 4, NULL, 11, 1, NULL, '');


INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '지출결의서', 1, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '휴가신청서', 2, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '경비보고서', 3, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '결재서류', 4, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '근로계약서', 5, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '출장보고서', 6, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '세금계산서', 7, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '계약서', 8, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '재고보고서', 9, NULL);
INSERT INTO cc_code (CODE_TYPE, CODE_NAME, COL_SEQ, SPARE) VALUES ('DOCUMENT', '프로젝트보고서', 10, NULL);
