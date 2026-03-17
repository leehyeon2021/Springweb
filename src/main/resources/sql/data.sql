-- day008
-- INSERT INTO todo(title, content, done, create_date, update_date) VALUES
--     ("스프링 부트 복습", "내장 서블릿 컨테이너 이해", FALSE, NOW(), NOW()),
--     ("SQL 코딩 테스트", "프로그래머스 고득점 Kit", TRUE, NOW(), NOW()),
--     ("운동하기", "스쿼트 100개, 푸쉬업 50개", FALSE, NOW(), NOW()),
--     ("독서", "클린 코드 3장 정독", TRUE, NOW(), NOW()),
--     ("블로그 포스팅", "TIL: 자바 예외 처리", FALSE, NOW(), NOW()),
--     ("프로젝트 기획", "포트폴리오용 웹앱 설계", FALSE, NOW(), NOW()),
--     ("영어 회화", "유튜브 쉐도잉 30분", TRUE, NOW(), NOW()),
--     ("방 정리", "겨울 옷 넣고 봄 옷 꺼내기", TRUE, NOW(), NOW()),
--     ("알고리즘 공부", "DFS/BFS 탐색 알고리즘", FALSE, NOW(), NOW()),
--     ("이력서 업데이트", "프로젝트 경험 추가", FALSE, NOW(), NOW());

--------------------------------------------------------------------------------------

-- day009
-- INSERT INTO article(title, content, created_at, updated_at) VALUES
--     ("제목 1", "내용 1", now(), now()),
--     ("제목 2", "내용 2", now(), now()),
--     ("제목 3", "내용 3", now(), now());

--------------------------------------------------------------------------------------

-- day11: todo sample insert
insert into category (cname, create_date, update_date) values ('공부', now(), now());
insert into category (cname, create_date, update_date) values ('운동', now(), now());
insert into category (cname, create_date, update_date) values ('업무', now(), now());
insert into category (cname, create_date, update_date) values ('취미', now(), now());
insert into category (cname, create_date, update_date) values ('생활', now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('자바 공부', 'JPA 기본 개념 정리', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('Spring Boot 실습', 'REST API 만들기', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('React 공부', 'useState와 props 이해하기', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('데이터베이스 공부', 'JOIN과 INDEX 복습', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('알고리즘 문제풀이', '백준 문제 5개 풀기', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('헬스장 가기', '하체 운동', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('러닝', '5km 달리기', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('스트레칭', '아침 스트레칭 20분', true, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('수영', '자유형 연습', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('요가', '유연성 운동', false, 2, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('프로젝트 기획', '서비스 기능 목록 정리', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('회의 준비', '자료 PPT 작성', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('코드 리뷰', '팀원 PR 검토', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('API 설계', 'ERD 및 엔드포인트 설계', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('배포 준비', 'Docker 이미지 빌드', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('기타 연습', '기타 코드 연습', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('독서', '기술 서적 30페이지 읽기', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('사진 촬영', '야경 촬영 연습', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('영화 감상', 'SF 영화 보기', true, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('드로잉', '인물 스케치', false, 4, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('장보기', '마트에서 식료품 구매', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('청소', '집 청소하기', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('세탁', '세탁기 돌리기', true, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('요리', '저녁 식사 준비', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('공과금 납부', '전기세 납부', false, 5, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('AI 공부', '머신러닝 개념 정리', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('딥러닝 실습', 'CNN 모델 구현', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('LLM 실습', 'OpenAI API 테스트', false, 1, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('팀 프로젝트', '프론트엔드 UI 구현', false, 3, now(), now());
insert into todo (title, content, done, cno, create_date, update_date) values('Git 정리', 'Git Flow 복습', false, 3, now(), now());

--------------------------------------------------------------------------------------

