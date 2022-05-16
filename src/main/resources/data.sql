INSERT INTO article(ID, title, content) VALUES (1, '가가가', '1111');
INSERT INTO article(ID, title, content) VALUES (2, '나나나', '2222');
INSERT INTO article(ID, title, content) VALUES (3, '다다다', '3333');


-- 댓글 - article 더미데이터 추가
INSERT INTO article(ID, title, content) VALUES (4, '당신의 취미는?', '댓글1');
INSERT INTO article(ID, title, content) VALUES (5, '당신의 소울 푸드는?', '댓글2');
INSERT INTO article(ID, title, content) VALUES (6, '당신의 인생 영화는?', '댓글3');


-- 댓글 - 4번 게시글에 대한 comment 더미데이터 추가
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (1,4, 'Kim', '숨쉬기');
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (2,4, 'Lee', '잠자기');
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (3,4, 'Choi', '숨쉬기2');

-- 댓글 - 5번 게시글에 대한 코멘트 더미데이터 추가
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (4,5, 'park', '치킨');
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (5,5, 'Kim', '죽');
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (6,5, 'youn', '피자');

-- 이하동문
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (7,6, 'Park', '명작1');
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (8,6, 'Hong', '영화2');
INSERT INTO comment(ID,article_ID,nickname, body) VALUES (9,6, 'Gong', '괴물');