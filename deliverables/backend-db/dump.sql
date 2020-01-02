--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: actor; Type: SCHEMA; Schema: -; Owner: bounswe9
--

CREATE SCHEMA actor;


ALTER SCHEMA actor OWNER TO bounswe9;

--
-- Name: mahmuthoca; Type: SCHEMA; Schema: -; Owner: bounswe9
--

CREATE SCHEMA mahmuthoca;


ALTER SCHEMA mahmuthoca OWNER TO bounswe9;

--
-- Name: proseidon; Type: SCHEMA; Schema: -; Owner: bounswe9
--

CREATE SCHEMA proseidon;


ALTER SCHEMA proseidon OWNER TO bounswe9;

--
-- Name: raven; Type: SCHEMA; Schema: -; Owner: bounswe9
--

CREATE SCHEMA raven;


ALTER SCHEMA raven OWNER TO bounswe9;

SET default_tablespace = '';

--
-- Name: annotations; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.annotations (
    id bigint NOT NULL,
    annotation character varying(8095) NOT NULL
);


ALTER TABLE actor.annotations OWNER TO bounswe9;

--
-- Name: annotations_id_seq; Type: SEQUENCE; Schema: actor; Owner: bounswe9
--

CREATE SEQUENCE actor.annotations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actor.annotations_id_seq OWNER TO bounswe9;

--
-- Name: annotations_id_seq; Type: SEQUENCE OWNED BY; Schema: actor; Owner: bounswe9
--

ALTER SEQUENCE actor.annotations_id_seq OWNED BY actor.annotations.id;


--
-- Name: assignments; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.assignments (
    id bigint NOT NULL,
    question character varying(255) NOT NULL,
    language_id bigint
);


ALTER TABLE actor.assignments OWNER TO bounswe9;

--
-- Name: comments; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.comments (
    id bigint NOT NULL,
    content character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    receiver_id bigint NOT NULL,
    source_id bigint NOT NULL
);


ALTER TABLE actor.comments OWNER TO bounswe9;

--
-- Name: conversations; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.conversations (
    id bigint NOT NULL,
    last_updated_at timestamp without time zone NOT NULL,
    user_id_one bigint NOT NULL,
    user_id_two bigint NOT NULL
);


ALTER TABLE actor.conversations OWNER TO bounswe9;

--
-- Name: essays; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.essays (
    id bigint NOT NULL,
    assignment_id bigint,
    author_id bigint,
    source character varying(255),
    type integer,
    source_type integer
);


ALTER TABLE actor.essays OWNER TO bounswe9;

--
-- Name: exercises; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.exercises (
    id bigint NOT NULL,
    grade integer NOT NULL,
    image_url character varying(255),
    language_id integer NOT NULL,
    sound_url character varying(255),
    type_id integer,
    correct_answer integer NOT NULL,
    option_a character varying(255) NOT NULL,
    option_b character varying(255) NOT NULL,
    option_c character varying(255) NOT NULL,
    option_d character varying(255) NOT NULL,
    question_body character varying(255) NOT NULL
);


ALTER TABLE actor.exercises OWNER TO bounswe9;

--
-- Name: grades; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.grades (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    language_id integer NOT NULL,
    grade integer NOT NULL
);


ALTER TABLE actor.grades OWNER TO bounswe9;

--
-- Name: grades_id_seq; Type: SEQUENCE; Schema: actor; Owner: bounswe9
--

CREATE SEQUENCE actor.grades_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actor.grades_id_seq OWNER TO bounswe9;

--
-- Name: grades_id_seq; Type: SEQUENCE OWNED BY; Schema: actor; Owner: bounswe9
--

ALTER SEQUENCE actor.grades_id_seq OWNED BY actor.grades.id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: actor; Owner: bounswe9
--

CREATE SEQUENCE actor.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actor.hibernate_sequence OWNER TO bounswe9;

--
-- Name: invitations; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.invitations (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    receiver_id bigint NOT NULL,
    source_id bigint NOT NULL
);


ALTER TABLE actor.invitations OWNER TO bounswe9;

--
-- Name: invitations_id_seq; Type: SEQUENCE; Schema: actor; Owner: bounswe9
--

CREATE SEQUENCE actor.invitations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actor.invitations_id_seq OWNER TO bounswe9;

--
-- Name: invitations_id_seq; Type: SEQUENCE OWNED BY; Schema: actor; Owner: bounswe9
--

ALTER SEQUENCE actor.invitations_id_seq OWNED BY actor.invitations.id;


--
-- Name: languages; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.languages (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE actor.languages OWNER TO bounswe9;

--
-- Name: messages; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.messages (
    id bigint NOT NULL,
    content character varying(255) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    receiver_id bigint NOT NULL,
    source_id bigint NOT NULL
);


ALTER TABLE actor.messages OWNER TO bounswe9;

--
-- Name: ratings; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.ratings (
    id bigint NOT NULL,
    rating integer NOT NULL,
    receiver_id bigint NOT NULL,
    sender_id bigint NOT NULL
);


ALTER TABLE actor.ratings OWNER TO bounswe9;

--
-- Name: requests; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.requests (
    id bigint NOT NULL,
    essay_id bigint,
    receiver_id bigint,
    source_id bigint
);


ALTER TABLE actor.requests OWNER TO bounswe9;

--
-- Name: solved_exercises; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.solved_exercises (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    exercise_id bigint NOT NULL
);


ALTER TABLE actor.solved_exercises OWNER TO bounswe9;

--
-- Name: solved_exercises_id_seq; Type: SEQUENCE; Schema: actor; Owner: bounswe9
--

CREATE SEQUENCE actor.solved_exercises_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actor.solved_exercises_id_seq OWNER TO bounswe9;

--
-- Name: solved_exercises_id_seq; Type: SEQUENCE OWNED BY; Schema: actor; Owner: bounswe9
--

ALTER SEQUENCE actor.solved_exercises_id_seq OWNED BY actor.solved_exercises.id;


--
-- Name: tags; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.tags (
    id bigint NOT NULL,
    exercise_id bigint,
    tag_text character varying(255)
);


ALTER TABLE actor.tags OWNER TO bounswe9;

--
-- Name: users; Type: TABLE; Schema: actor; Owner: bounswe9
--

CREATE TABLE actor.users (
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE actor.users OWNER TO bounswe9;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: actor; Owner: bounswe9
--

CREATE SEQUENCE actor.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE actor.users_id_seq OWNER TO bounswe9;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: actor; Owner: bounswe9
--

ALTER SEQUENCE actor.users_id_seq OWNED BY actor.users.id;


--
-- Name: assignments; Type: TABLE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE TABLE mahmuthoca.assignments (
    id bigint NOT NULL,
    question character varying(255) NOT NULL,
    language_id bigint
);


ALTER TABLE mahmuthoca.assignments OWNER TO bounswe9;

--
-- Name: assignments_id_seq; Type: SEQUENCE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE SEQUENCE mahmuthoca.assignments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mahmuthoca.assignments_id_seq OWNER TO bounswe9;

--
-- Name: assignments_id_seq; Type: SEQUENCE OWNED BY; Schema: mahmuthoca; Owner: bounswe9
--

ALTER SEQUENCE mahmuthoca.assignments_id_seq OWNED BY mahmuthoca.assignments.id;


--
-- Name: comments; Type: TABLE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE TABLE mahmuthoca.comments (
    id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    source_id bigint NOT NULL,
    created_at date NOT NULL,
    content character varying(255) NOT NULL
);


ALTER TABLE mahmuthoca.comments OWNER TO bounswe9;

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE SEQUENCE mahmuthoca.comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mahmuthoca.comments_id_seq OWNER TO bounswe9;

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: mahmuthoca; Owner: bounswe9
--

ALTER SEQUENCE mahmuthoca.comments_id_seq OWNED BY mahmuthoca.comments.id;


--
-- Name: essays; Type: TABLE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE TABLE mahmuthoca.essays (
    id bigint NOT NULL,
    assignment_id bigint NOT NULL,
    author_id bigint NOT NULL,
    source character varying(16023) NOT NULL,
    source_type integer NOT NULL
);


ALTER TABLE mahmuthoca.essays OWNER TO bounswe9;

--
-- Name: essays_id_seq; Type: SEQUENCE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE SEQUENCE mahmuthoca.essays_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mahmuthoca.essays_id_seq OWNER TO bounswe9;

--
-- Name: essays_id_seq; Type: SEQUENCE OWNED BY; Schema: mahmuthoca; Owner: bounswe9
--

ALTER SEQUENCE mahmuthoca.essays_id_seq OWNED BY mahmuthoca.essays.id;


--
-- Name: ratings; Type: TABLE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE TABLE mahmuthoca.ratings (
    id bigint NOT NULL,
    sender_id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    rating integer NOT NULL
);


ALTER TABLE mahmuthoca.ratings OWNER TO bounswe9;

--
-- Name: ratings_id_seq; Type: SEQUENCE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE SEQUENCE mahmuthoca.ratings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mahmuthoca.ratings_id_seq OWNER TO bounswe9;

--
-- Name: ratings_id_seq; Type: SEQUENCE OWNED BY; Schema: mahmuthoca; Owner: bounswe9
--

ALTER SEQUENCE mahmuthoca.ratings_id_seq OWNED BY mahmuthoca.ratings.id;


--
-- Name: requests; Type: TABLE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE TABLE mahmuthoca.requests (
    id bigint NOT NULL,
    source_id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    essay_id bigint NOT NULL
);


ALTER TABLE mahmuthoca.requests OWNER TO bounswe9;

--
-- Name: requests_id_seq; Type: SEQUENCE; Schema: mahmuthoca; Owner: bounswe9
--

CREATE SEQUENCE mahmuthoca.requests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mahmuthoca.requests_id_seq OWNER TO bounswe9;

--
-- Name: requests_id_seq; Type: SEQUENCE OWNED BY; Schema: mahmuthoca; Owner: bounswe9
--

ALTER SEQUENCE mahmuthoca.requests_id_seq OWNED BY mahmuthoca.requests.id;


--
-- Name: exercises; Type: TABLE; Schema: proseidon; Owner: bounswe9
--

CREATE TABLE proseidon.exercises (
    id bigint NOT NULL,
    language_id integer NOT NULL,
    type_id integer,
    image_url character varying(511),
    sound_url character varying(511),
    question_body character varying(1023) NOT NULL,
    option_a character varying(255) NOT NULL,
    option_b character varying(255) NOT NULL,
    option_c character varying(255) NOT NULL,
    option_d character varying(255) NOT NULL,
    correct_answer integer NOT NULL,
    grade integer
);


ALTER TABLE proseidon.exercises OWNER TO bounswe9;

--
-- Name: exercises_id_seq; Type: SEQUENCE; Schema: proseidon; Owner: bounswe9
--

CREATE SEQUENCE proseidon.exercises_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proseidon.exercises_id_seq OWNER TO bounswe9;

--
-- Name: exercises_id_seq; Type: SEQUENCE OWNED BY; Schema: proseidon; Owner: bounswe9
--

ALTER SEQUENCE proseidon.exercises_id_seq OWNED BY proseidon.exercises.id;


--
-- Name: languages; Type: TABLE; Schema: proseidon; Owner: bounswe9
--

CREATE TABLE proseidon.languages (
    id integer NOT NULL,
    name character varying(31) NOT NULL
);


ALTER TABLE proseidon.languages OWNER TO bounswe9;

--
-- Name: languages_id_seq; Type: SEQUENCE; Schema: proseidon; Owner: bounswe9
--

CREATE SEQUENCE proseidon.languages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proseidon.languages_id_seq OWNER TO bounswe9;

--
-- Name: languages_id_seq; Type: SEQUENCE OWNED BY; Schema: proseidon; Owner: bounswe9
--

ALTER SEQUENCE proseidon.languages_id_seq OWNED BY proseidon.languages.id;


--
-- Name: tags; Type: TABLE; Schema: proseidon; Owner: bounswe9
--

CREATE TABLE proseidon.tags (
    id bigint NOT NULL,
    exercise_id bigint NOT NULL,
    tag_text character varying(63) NOT NULL
);


ALTER TABLE proseidon.tags OWNER TO bounswe9;

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: proseidon; Owner: bounswe9
--

CREATE SEQUENCE proseidon.tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proseidon.tags_id_seq OWNER TO bounswe9;

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: proseidon; Owner: bounswe9
--

ALTER SEQUENCE proseidon.tags_id_seq OWNED BY proseidon.tags.id;


--
-- Name: conversations; Type: TABLE; Schema: raven; Owner: bounswe9
--

CREATE TABLE raven.conversations (
    id bigint NOT NULL,
    user_id_one bigint NOT NULL,
    user_id_two bigint NOT NULL,
    last_updated_at timestamp without time zone NOT NULL
);


ALTER TABLE raven.conversations OWNER TO bounswe9;

--
-- Name: conversations_id_seq; Type: SEQUENCE; Schema: raven; Owner: bounswe9
--

CREATE SEQUENCE raven.conversations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE raven.conversations_id_seq OWNER TO bounswe9;

--
-- Name: conversations_id_seq; Type: SEQUENCE OWNED BY; Schema: raven; Owner: bounswe9
--

ALTER SEQUENCE raven.conversations_id_seq OWNED BY raven.conversations.id;


--
-- Name: messages; Type: TABLE; Schema: raven; Owner: bounswe9
--

CREATE TABLE raven.messages (
    id bigint NOT NULL,
    source_id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    content character varying(511) NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE raven.messages OWNER TO bounswe9;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: raven; Owner: bounswe9
--

CREATE SEQUENCE raven.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE raven.messages_id_seq OWNER TO bounswe9;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: raven; Owner: bounswe9
--

ALTER SEQUENCE raven.messages_id_seq OWNED BY raven.messages.id;


--
-- Name: annotations id; Type: DEFAULT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.annotations ALTER COLUMN id SET DEFAULT nextval('actor.annotations_id_seq'::regclass);


--
-- Name: grades id; Type: DEFAULT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.grades ALTER COLUMN id SET DEFAULT nextval('actor.grades_id_seq'::regclass);


--
-- Name: invitations id; Type: DEFAULT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.invitations ALTER COLUMN id SET DEFAULT nextval('actor.invitations_id_seq'::regclass);


--
-- Name: solved_exercises id; Type: DEFAULT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.solved_exercises ALTER COLUMN id SET DEFAULT nextval('actor.solved_exercises_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.users ALTER COLUMN id SET DEFAULT nextval('actor.users_id_seq'::regclass);


--
-- Name: assignments id; Type: DEFAULT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.assignments ALTER COLUMN id SET DEFAULT nextval('mahmuthoca.assignments_id_seq'::regclass);


--
-- Name: comments id; Type: DEFAULT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.comments ALTER COLUMN id SET DEFAULT nextval('mahmuthoca.comments_id_seq'::regclass);


--
-- Name: essays id; Type: DEFAULT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.essays ALTER COLUMN id SET DEFAULT nextval('mahmuthoca.essays_id_seq'::regclass);


--
-- Name: ratings id; Type: DEFAULT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.ratings ALTER COLUMN id SET DEFAULT nextval('mahmuthoca.ratings_id_seq'::regclass);


--
-- Name: requests id; Type: DEFAULT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.requests ALTER COLUMN id SET DEFAULT nextval('mahmuthoca.requests_id_seq'::regclass);


--
-- Name: exercises id; Type: DEFAULT; Schema: proseidon; Owner: bounswe9
--

ALTER TABLE ONLY proseidon.exercises ALTER COLUMN id SET DEFAULT nextval('proseidon.exercises_id_seq'::regclass);


--
-- Name: languages id; Type: DEFAULT; Schema: proseidon; Owner: bounswe9
--

ALTER TABLE ONLY proseidon.languages ALTER COLUMN id SET DEFAULT nextval('proseidon.languages_id_seq'::regclass);


--
-- Name: tags id; Type: DEFAULT; Schema: proseidon; Owner: bounswe9
--

ALTER TABLE ONLY proseidon.tags ALTER COLUMN id SET DEFAULT nextval('proseidon.tags_id_seq'::regclass);


--
-- Name: conversations id; Type: DEFAULT; Schema: raven; Owner: bounswe9
--

ALTER TABLE ONLY raven.conversations ALTER COLUMN id SET DEFAULT nextval('raven.conversations_id_seq'::regclass);


--
-- Name: messages id; Type: DEFAULT; Schema: raven; Owner: bounswe9
--

ALTER TABLE ONLY raven.messages ALTER COLUMN id SET DEFAULT nextval('raven.messages_id_seq'::regclass);


--
-- Data for Name: annotations; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.annotations (id, annotation) FROM stdin;
1	{ "source": "arda", "fdd": "bb" }
3	{"field":"hasan", "field2":"mahmut"}
4	{"field":"http://www.w3.org/TR/media-frags/", "field2":"mahmut"}
5	{"field":"http://www.w3.org/TR/media-frags/", "field2":"mahmut"}
6	{ "url": "http://www.w3.org/TR/media-frags/" }
7	{ "url": "http://www.w3.org/ns/anno.jsonld/" }
8	{ 'url': 'http://www.w3.org/ns/anno.jsonld/', 'neden': 'singlequote' }
12	{'targetId': 'http://example.com/writing1#char=0,10', 'value' : 'Bad Mistake'}
13	{'targetId': 'http://example.com/writing1#char=0,10', 'value' : 'Bad Mistake'}
16	{ "field":"hasan", "field2":"mahmut"}
17	{ "domates":"patates" }
18	{ "hasan": "mustafa" }
19	{ "dasdsa": " 5635 " }
20	{ "dasdsa": " 5635 " }
10	{ "domat":"patat" }
11	{ "hasan": "masan" }
37	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/22","name":"Mahmut Kızıloğlu","email":"mahmut.kiziloglu@hotmail.com"},"created":"2019-12-24T14:43:10.115Z","modified":"2019-12-24T14:43:19.094Z","body":{"type":"TextualBody","value":"kjhkjhjkhlkjhliutuyftsrtyuiuytr","format":"text/plain"},"motivation":"assessing","target":{"id":"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=24","type":"Text","format":"text/plain","selector":{"type":"FragmentSelector","conformsTo":"http://tools.ietf.org/rfc/rfc5147","value":"char=323,482"}}}
38	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/22","name":"Mahmut Kızıloğlu","email":"mahmut.kiziloglu@hotmail.com"},"created":"2019-12-24T14:43:29.904Z","modified":"2019-12-24T14:43:33.008Z","body":{"type":"TextualBody","value":"jgt","format":"text/plain"},"motivation":"commenting","target":{"id":"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=24","type":"Text","format":"text/plain","selector":{"type":"FragmentSelector","conformsTo":"http://tools.ietf.org/rfc/rfc5147","value":"char=180,192"}}}
21	{ "dom": "pom" }
25	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T10:55:49.132Z","modified":"2019-12-24T11:18:45.251Z","body":{"type":"TextualBody","value":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","format":"text/plain"},"motivation":"describing","target":{"id":"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=17","type":"Text","format":"text/plain","selector":{"type":"FragmentSelector","conformsTo":"http://tools.ietf.org/rfc/rfc5147","value":"char=3,5"}}}
26	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T11:30:44.664Z","modified":"2019-12-24T11:36:44.649Z","body":{"type":"TextualBody","value":"Well done here","format":"text/plain"},"motivation":"assessing","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_30.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=56,209,305,46"}}}
27	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T11:30:56.984Z","modified":"2019-12-24T11:36:49.841Z","body":{"type":"TextualBody","value":"Oh my god, great!!","format":"text/plain"},"motivation":"describing","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_30.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=54,321,88,181"}}}
28	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T12:23:03.494Z","modified":"2019-12-24T12:23:09.048Z","body":{"type":"TextualBody","value":"Wow dude","format":"text/plain"},"motivation":"commenting","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=138,193,60,30"}}}
29	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T12:23:10.269Z","modified":"2019-12-24T12:23:22.445Z","body":{"type":"TextualBody","value":"This is the best thing I've ever seen","format":"text/plain"},"motivation":"bookmarking","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=360,516,171,30"}}}
33	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/9","name":"irem Uguz","email":"irem@test.com"},"created":"2019-12-24T13:30:48.093Z","modified":"2019-12-24T13:31:04.987Z","body":{"type":"TextualBody","value":"burası net","format":"text/plain"},"motivation":"classifying","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=277,307,60,30"}}}
30	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T12:23:23.805Z","modified":"2019-12-24T12:23:33.583Z","body":{"type":"TextualBody","value":"It even supports scroll, woah","format":"text/plain"},"motivation":"highlighting","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=138,642,60,30"}}}
32	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/9","name":"irem Uguz","email":"irem@test.com"},"created":"2019-12-24T13:30:17.798Z","modified":"2019-12-24T13:30:44.551Z","body":{"type":"TextualBody","value":"Merhaba bunu ben yazdım","format":"text/plain"},"motivation":"bookmarking","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=301,45,201,215"}}}
31	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/12","name":"Johnie Walker","email":"j.walker@hotmail.com"},"created":"2019-12-24T12:24:11.645Z","modified":"2019-12-24T12:24:49.757Z","body":{"type":"TextualBody","value":"I know right!!","format":"text/plain"},"motivation":"moderating","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=18,394,75,56"}}}
34	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/41","name":"Doğan Çavdarcı","email":"dogan.cavdarci@hotmail.com"},"created":"2019-12-24T13:32:15.392Z","modified":"2019-12-24T13:32:26.445Z","body":{"type":"TextualBody","value":"burası olmamıs düzelecek","format":"text/plain"},"motivation":"editing","target":{"id":"https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg","type":"Image","format":"image/jpeg","selector":{"type":"FragmentSelector","conformsTo":"http://www.w3.org/TR/media-frags/","value":"xywh=397,290,60,30"}}}
35	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/30","name":"John Johnwell","email":"john@johnwell.com"},"created":"2019-12-24T13:37:19.537Z","modified":"2019-12-24T13:37:23.015Z","body":{"type":"TextualBody","value":"asdasdas","format":"text/plain"},"motivation":"describing","target":{"id":"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=17","type":"Text","format":"text/plain","selector":{"type":"FragmentSelector","conformsTo":"http://tools.ietf.org/rfc/rfc5147","value":"char=0,3"}}}
36	{"@context":"http://www.w3.org/ns/anno.jsonld","type":"Annotation","creator":{"id":"https://bounswe2019group9.tk/users/22","name":"Mahmut Kızıloğlu","email":"mahmut.kiziloglu@hotmail.com"},"created":"2019-12-24T14:42:56.554Z","modified":"2019-12-24T14:43:01.283Z","body":{"type":"TextualBody","value":"jhg","format":"text/plain"},"motivation":"highlighting","target":{"id":"https://api.bounswe2019group9.tk/essays/getSourceByEssayId?id=24","type":"Text","format":"text/plain","selector":{"type":"FragmentSelector","conformsTo":"http://tools.ietf.org/rfc/rfc5147","value":"char=152,456"}}}
\.


--
-- Data for Name: assignments; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.assignments (id, question, language_id) FROM stdin;
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.comments (id, content, created_at, receiver_id, source_id) FROM stdin;
\.


--
-- Data for Name: conversations; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.conversations (id, last_updated_at, user_id_one, user_id_two) FROM stdin;
\.


--
-- Data for Name: essays; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.essays (id, assignment_id, author_id, source, type, source_type) FROM stdin;
\.


--
-- Data for Name: exercises; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.exercises (id, grade, image_url, language_id, sound_url, type_id, correct_answer, option_a, option_b, option_c, option_d, question_body) FROM stdin;
\.


--
-- Data for Name: grades; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.grades (id, user_id, language_id, grade) FROM stdin;
1	8	1	5
2	3	1	3
3	12	1	3
4	12	1	1
5	12	1	1
6	12	1	1
7	12	1	1
8	12	1	1
9	12	1	1
10	12	1	1
11	0	1	5
12	6	1	5
13	29	1	1
14	9	1	1
15	9	1	1
16	9	1	1
17	4	1	1
18	0	0	0
19	29	1	1
20	9	1	1
21	30	1	4
22	30	1	5
23	6	1	1
24	6	1	5
25	6	1	3
26	6	1	5
27	6	1	5
28	37	1	1
29	39	1	2
30	23	1	2
32	41	1	3
33	42	1	1
34	22	1	2
35	42	1	1
36	42	1	5
37	43	1	1
38	44	1	6
39	44	1	4
40	8	1	1
41	6	1	1
42	46	1	3
43	46	1	5
44	47	1	5
45	52	1	5
46	37	1	1
47	53	1	3
48	37	1	5
49	37	1	5
50	37	1	6
\.


--
-- Data for Name: invitations; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.invitations (id, created_at, receiver_id, source_id) FROM stdin;
10	2019-11-25 20:53:01.208	25	9
11	2019-11-26 01:30:59.109	2	30
14	2019-11-26 12:20:12.037	43	23
16	2019-12-01 18:11:44.67	1	6
17	2019-12-23 17:13:18.979	42	46
18	2019-12-23 20:37:45.755	30	48
20	2019-12-24 03:46:33.156	51	6
21	2019-12-24 10:18:08.641	44	50
22	2019-12-24 13:29:01.387	22	21
\.


--
-- Data for Name: languages; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.languages (id, name) FROM stdin;
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.messages (id, content, created_at, receiver_id, source_id) FROM stdin;
\.


--
-- Data for Name: ratings; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.ratings (id, rating, receiver_id, sender_id) FROM stdin;
\.


--
-- Data for Name: requests; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.requests (id, essay_id, receiver_id, source_id) FROM stdin;
\.


--
-- Data for Name: solved_exercises; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.solved_exercises (id, user_id, exercise_id) FROM stdin;
1	4	6
2	23	51
4	42	51
5	42	28
6	42	31
7	42	51
8	42	28
9	42	31
10	42	51
11	42	28
12	42	51
13	42	28
14	23	51
15	23	28
16	23	31
17	6	6
18	9	14
19	9	14
20	9	51
21	9	51
22	9	14
23	9	11
24	6	51
25	6	28
26	6	31
27	6	35
28	6	35
29	6	37
30	6	40
31	6	49
32	6	49
33	42	35
34	42	37
35	37	51
36	37	28
37	37	31
38	6	51
39	6	28
40	6	31
41	6	35
42	6	37
43	6	40
44	6	49
45	6	52
46	6	53
47	6	51
48	6	28
49	6	31
50	6	35
51	6	37
52	6	37
53	37	35
54	37	37
55	37	37
56	37	40
57	37	49
58	46	51
59	46	28
60	46	31
61	46	35
62	46	37
63	46	40
64	46	49
65	46	52
66	46	53
67	46	54
68	46	55
69	46	56
70	46	57
71	46	58
72	46	61
73	46	62
74	46	63
75	46	64
76	46	65
77	46	66
78	46	32
79	46	33
80	46	34
81	46	36
82	46	45
83	46	67
84	37	52
85	37	53
86	37	53
87	37	54
88	37	55
89	37	56
90	37	57
91	37	58
92	37	58
93	37	61
94	37	61
95	37	62
96	37	63
97	37	64
98	37	64
99	37	65
100	37	66
101	37	32
102	37	33
103	37	34
104	37	36
105	37	45
106	37	67
107	37	68
\.


--
-- Data for Name: tags; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.tags (id, exercise_id, tag_text) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: actor; Owner: bounswe9
--

COPY actor.users (email, password, first_name, last_name, id) FROM stdin;
ahmettest@testtest.coam	9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08	ahmet	test	2
ahmettestttesttt@testtt.comtest	asdgfsjdfa	ahmet	test	1
ahmettesttttt@testtest.coam	9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08	ahmet	test	3
ahmettesttttttt@testtest.coam	9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08	ahmet	test	4
test@fortest.com	9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08	testname	testtest	5
test@test.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	testname	testsurname	6
ick@test.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Ick	Testing	7
gamze@test.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	gamze	gamze	8
maymun@sun.me	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	s	maymun	10
a@a.com	ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb	a	a	11
j.walker@hotmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Johnie	Walker	12
gg@gmail.com	a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3	gamze	gul	13
test@test.com1	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Test	Test	14
ahmad@test.com	96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e	Ahmet	Abizuddin	15
aysegul@gmail.com	a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3	ayse	gul	16
ab@test.com	216d77bbeb18263df515aa39833f2a00ca86b5d4443f5f4027ab64f4fb09edd4	aa	bb	17
egemen@egemen.com11	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Egemen	Gol	18
egemen@egemen.com1	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Egemen	Gol	19
afgedemenli@gmail.com	bc00a079ba201e1e39600798848ddaf15f281f82178de86af087577ef599c799	Ahmet	Gedemenli	20
ahmetoglu.alper@gmail.com	5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8	alper	ahmetoglu	21
francis@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Francisco	Terrega	23
sdf@asd.com	a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3	as	sdf	24
catalahmet@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	ahmet	catal	25
kim@kim.com	3ea36dbb739d60ac8aacbfc9aa7db7d8ed871e10a3faba5a4dd33c21e11a1a61	kimbu	kimki	26
abc@mail.com	283cb4a858554277b81ac2129ea4f35d3793bc0c6612ddb10757cdfbc8d22347	Osman	Kaya	27
cc@gmail.com	a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3	Aa	Bb	28
a@gmail.com	36f583dd16f4e1e201eb1e6f6d8e35a2ccb3bbe2658de46b4ffae7b0e9ed872e	aa	bb	29
john@johnwell.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	John	Johnwell	30
arda@gmail.com	9d777935627a29c77604c57273520eb42635fd1847d2eeea1e7441fbaeb26253	arda	budak	31
ardabudak@gmail.com	9d777935627a29c77604c57273520eb42635fd1847d2eeea1e7441fbaeb26253	arda	budak	33
barisbudak@gmail.com	b875f4ce34e30df3cd299442e471da5a6d07ce04b592a77ebf402573d42b3e60	baris	budak	34
blakey@pmail.com	e8f56862d74ef5599af4eeca73924bfa44a6773a497af0c29c48e18729ba6ff0	art	blakey	35
emanuel@ymail.com	6f39e60854f30d02c988e65ba9a9fbd7daba851f0fd4e5227de5b6d2a0de99f2	emanuel	cant	36
ggulbahar@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Gamze	Gülbahar	37
whodis@hotmail.com	ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f	W	D	38
egemen@test.com	a78877905a73326fcb3d0c8f38de99582c6921b67517287d374c58c5c8e7f574	egemen	göl	39
dogan.cavdarci@hotmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Doğan	Çavdarcı	41
xavier@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Xavier	Hernandez	42
mahmut.kiziloglu@hotmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Mahmut	Kızıloğlu	22
mj@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Mary	Jane	43
e.yasincetin@gmail.com	76e850744a6fe4464c76645e83df8d9d5da2ca87d8bebd4e22694824d81ea0fd	emirhan	emirhan	44
baba@gmail.com	a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3	Emirhan Yasin	Cetin	45
isabel.castilla@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	Isabel	Castilla	46
irem@test.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	irem	Uguz	9
jamesbond@gmail.com	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	james	bond	47
dummy@dummy.com	b5a2c96250612366ea272ffac6d9744aaf4b45aacd96aa7cfcb931ee3b558259	Dum	My	48
ebasural@gmail.com	03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4	ege	ege	49
baba@baba.com	e75a6cd43a16c2f31d1a3c17700af64d3658a380c49d65b20cc75b1f7c0e001b	baba	baba	50
metres@test.com	8c20618624aa37d787edee4f675241376aba8930a527c8ce9e02812572871b28	metres	söylenmez	51
zcagriyeni@gmail.com	cc70026cf6f1e7762a7bfebba020b770f5054cf74f7768d04c95ee0b40d6c70f	Cagri	Yeni	52
selimtoprak@outlook.com	a595e543ef73ab39facc5c1b3371de0c5146d5a37588da76602dbd3a7dc5d714	Selim	Selim	53
\.


--
-- Data for Name: assignments; Type: TABLE DATA; Schema: mahmuthoca; Owner: bounswe9
--

COPY mahmuthoca.assignments (id, question, language_id) FROM stdin;
3	Describe a place you will never forget.	1
4	Describe a sporting event you attended recently.	1
5	Describe someone you respect deeply.	1
6	Describe your childhood home.	1
7	Describe the nightlife in a city you are familiar with.	1
8	What would you do if you got lost in an unfamiliar city?	1
9	What would you do if you left something in a locked building?	1
1	What is your opinion about fortune telling?	1
2	What is your opinion about cellular phones?	1
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: mahmuthoca; Owner: bounswe9
--

COPY mahmuthoca.comments (id, receiver_id, source_id, created_at, content) FROM stdin;
1	3	1	2019-12-14	nice
2	1	2	2019-12-15	deneme
3	9	42	2019-12-15	Deneme comment
4	8	9	2019-12-17	very good reviews
5	9	6	2019-12-23	I love you
6	9	6	2019-12-23	I love you
7	42	46	2019-12-23	He helped me a lot with my essays. Great teacher!
8	39	48	2019-12-23	Excellent person! 
9	39	48	2019-12-23	Excellent person! 
10	29	30	2019-12-24	asdw
11	29	30	2019-12-24	My second comment, with minutes in between.\nMulti-line.
12	29	30	2019-12-24	hello from the otherside...
13	29	30	2019-12-24	my musings
14	48	30	2019-12-24	asjdas
15	48	30	2019-12-24	uyqwehjd
16	46	42	2019-12-24	Her feedback was so helpful for me. I recommend her to everybody. 
17	43	46	2019-12-24	She helped me a lot with my exercise.
\.


--
-- Data for Name: essays; Type: TABLE DATA; Schema: mahmuthoca; Owner: bounswe9
--

COPY mahmuthoca.essays (id, assignment_id, author_id, source, source_type) FROM stdin;
2	1	9	kjdgkjnkjkgdkjangkjdrngkjdangkjadfn kjdfkjadgkjadhgkjdahfkjadhgkjadfh kjdfkjdhgkjdfhgkjfdhg	1
13	2	37	ilkjhgfdsdfghjkl\n\n\n\n\nlkjhgfdshljkpil,pkojhkugjyfhdxzghuljokpilkjlhkugjfcxdghjklpüi\n	1
15	2	46	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/2_46.jpeg	2
16	1	30	string	0
17	2	30	string	1
18	6	12	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_12.jpeg	2
19	6	30	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/6_30.jpeg	2
20	3	30	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_30.jpeg	2
22	3	9	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/essay/3_9.jpeg	2
23	6	22	deneme	1
24	9	22	North Coast Section Foundation Scholarship for 1000 by Christine Fung\nAs a child of immigrant parents I learned to take responsibilities for my family and myself at a very young age Although my parents spoke English they constantly worked in order to financially support my little brother and I Meanwhile my grandparents barely knew English so I became their translator for medical appointments and in every single interaction with English speakers Even until now I still translate for them and I teach my grandparents conversational English The more involved I became with my family the more I knew what I wanted to be in the future	1
\.


--
-- Data for Name: ratings; Type: TABLE DATA; Schema: mahmuthoca; Owner: bounswe9
--

COPY mahmuthoca.ratings (id, sender_id, receiver_id, rating) FROM stdin;
1	18	3	3
2	3	18	3
3	4	18	5
4	5	18	5
5	42	9	3
6	9	2	3
7	9	8	3
8	6	9	5
9	46	42	5
10	48	39	4
11	30	29	3
12	23	24	4
13	27	28	5
14	30	39	3
15	42	46	5
16	46	43	4
17	37	9	5
\.


--
-- Data for Name: requests; Type: TABLE DATA; Schema: mahmuthoca; Owner: bounswe9
--

COPY mahmuthoca.requests (id, source_id, receiver_id, essay_id) FROM stdin;
1	4	6	1
2	2	1	1
3	30	39	17
4	30	42	19
5	12	46	18
6	12	42	18
7	12	28	18
8	12	18	18
9	12	9	18
10	12	24	18
11	12	39	18
12	12	29	18
13	12	8	18
14	12	3	18
15	12	2	18
16	12	51	18
17	12	50	18
18	12	49	18
19	12	48	18
20	12	47	18
21	12	45	18
22	12	44	18
23	12	22	18
24	12	43	18
25	12	41	18
26	12	38	18
27	12	37	18
28	12	36	18
29	12	35	18
30	12	34	18
31	12	33	18
32	12	31	18
33	12	30	18
34	12	27	18
35	12	26	18
36	12	25	18
37	12	23	18
38	12	21	18
39	12	20	18
40	12	19	18
41	12	17	18
42	12	16	18
43	12	15	18
44	12	14	18
45	12	13	18
46	12	12	18
47	12	11	18
48	12	10	18
49	12	7	18
50	12	6	18
51	12	5	18
52	12	1	18
53	12	4	18
54	9	41	22
\.


--
-- Data for Name: exercises; Type: TABLE DATA; Schema: proseidon; Owner: bounswe9
--

COPY proseidon.exercises (id, language_id, type_id, image_url, sound_url, question_body, option_a, option_b, option_c, option_d, correct_answer, grade) FROM stdin;
51	1	3	\N	\N	Tennis is a wonderful game. To ___ tennis well you have to be very determined. I ___ playing tennis.	play, enjoy 	 perform, like 	excute, enjoy	play, live 	1	2
14	1	2			I can't imagine any reason _______ he should have behaved in such an extraordinary way.	for	that	how	why	4	3
10	0	0			Which one of these is not correlated?	eat - out	have – parties	study – together	play – movies at home	3	2
11	1	2			A stranger is ...........................	a buddy	someone you don’t know	a good friend	someone you count on	2	3
12	1	2			 useless; hopeless; ineffectual	futile	presumptuous	reverent	prestigious	1	1
15	1	2			Can I park here?	Sorry, I did that.	It's the same place.	Happy birthday park.	Why?	4	1
16	1	2			He hasn't come home ........	still	till	yet	already	3	2
19	1	2			When the building was completed, all the workers were paid ........	off	out	through	over	1	2
17	1	2			The boss was good enough to ....... my mistake.	oversee	overdo	overlook	overtake	3	3
18	1	2			At the end of the speech the whole assembly gave the speakers a standing ........	support	cheering	applause	ovation	4	1
13	1	2			_______ he should have spent all the weekend preparing for his test, he in fact just lay in bed watching videos.	however	whereas	despite	nevertheless	2	2
28	1	3			An apple is a ?	fruit	vegetable	tool	machine	1	2
30	1	1		https://listenaminute.com/x/x-rays.mp3	According to him, X-ray machines were pretty high-tech ___ years ago	thirty	fourty	fifty	sixty	3	2
31	1	3	https://img.icons8.com/color/96/000000/rain.png		How is the weather in this picture?	sunny	rainy	stormy	foggy	2	2
32	1	4			 Which one of these sentences is gramatically correct? 	Fiona decided having a shower after dinner.	I stopped work having a cup of coffee.	Having children will change your life.	I am having two houses.	3	2
33	1	4			My wife is more intelligent ___ me.	from	of	to	than	4	2
34	1	4			You ___ eat your meat if you want to have pudding.	must to	has to	are not	have to	4	2
35	1	3	https://img.icons8.com/color/96/000000/waterfall.png		What is this?	a river	a waterfall	a forest	a dam	2	2
36	1	4			What will you do if ___?	everything goes wrong	everything will go wrong	everything might go wrong	everyhting would go wrong	1	2
37	1	3	https://img.icons8.com/color/96/000000/mouse-animal.png		What is the name of this animal?	mouse	squirrel	cat	tortoise	1	2
38	1	2			A crow was sitting on a branch of a tree with a piece of cheese in its beak when a fox came up and saw him. He really wanted that cheese for himself. He stood under the tree and said that if only his voice was as beautiful as his feathers, he would be the best bird there could be! The crow was flattered with this and opened his beak to show the fox how beautifully he could sing. Of course, the cheese fell to the ground. The fox grabbed it and criticized the crow for not being witty despite his vocal skills. What happened when the crow felt flattered and began to sing to show how beautiful his voice was?	The cheese fell to the ground.	His voice was beautiful.	The fox sang with him.	The fox fled.	1	2
39	1	2			When ticket office is closed please use ticket machine. Passengers travelling without a ticket may receive a fine. This notice tells passengers ___	where to find the ticket office	to buy a ticket before travelling.	where to find the ticket machine.	that the ticket machine is not working.	2	2
40	1	3	https://img.icons8.com/doodle/96/000000/moon-and-sun.png		The sun is ___ by the Moon.	supported	adopted	stolen	eclipsed	4	2
41	1	1		https://listenaminute.com/t/tennis.mp3	What were his arguments with his friends about?	How great tennis is	Whether a three year old child can play tennis or not	Whether the ball was in or out	Which tournament is the best	3	2
42	1	2			College education is an important requirement for a fulfilling career in modern world. A company may not even think about hiring someone without a college degree when it is hiring for a white collar position. However, in some countries where there is no option of going to college without paying a substantial amount of money as tuition, many students from economically disadvantaged backgrounds either cannot get a college education or graduate college with a considerable amount of debt. Therefore I believe public colleges should be tuition free for students from an economically disadvantaged background because this would ensure equality of opportunity and economic growth. What is the author's thesis in this paragraph?	Not everyone should go to college.	Not everyone should be required to pay for college.	All colleges should be free.	Economic growth is the solution to all societal problems.	2	2
44	1	1		https://listenaminute.com/v/video_games.mp3	Why is the Wii OK, according to the speaker?	It's sound quality is good.	It is not addictive.	It has built-in accelerometers.	You can get some exercise while you are playing it.	4	2
45	1	4			Jared ___ sleeping when Ellen called him.	are	was	were	is	2	2
46	1	1		https://listenaminute.com/w/websites.mp3	Which one is not mentioned among things that we use websites for?	sending mails	chatting	gaming	shopping	4	2
47	1	2			Buses leave from this stop at 05:30, then every 15 minutes until 18:00, then hourly until 22:00. There is not a bus at ___	19:00	05:45	18:30	21:00	3	2
48	1	2			The sandwich they served me did not look like the one in the brochure. ___	It was tastier.	You didn't mention that it contains anchovies.	It looks better in the picture.	It smelled better in the brochure.	3	2
49	1	3	https://img.icons8.com/clouds/100/000000/happy.png		The Sun is ___	joyful	furious	bored	disappointed	1	2
50	1	1		https://listenaminute.com/r/rice.mp3	Billions of people probably eat rice ___ times a day.	one	two	three	four	3	2
6	1	2	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/exercise/6.png		Which one of these is a fruit?	Bean	Potato	Bread	Apple	4	1
52	1	3	\N	\N	Which is a color	red	ahmet	veli	deniz	1	1
53	1	3	\N	\N	Which is a color	red	ahmet	veli	deniz	1	1
54	1	3	\N	\N	Which is a color	red	ahmet	veli	deniz	1	1
55	1	3	\N	\N	What is a color	red	ahmet	veli	deniz	1	1
56	1	3	\N	\N	Which is a color	blue	ahmet	ali	veli	1	2
57	1	3	\N	\N	Which is a color	yyyy	gmcmc	mvvmmvö	kvkvkv	1	1
58	1	3	\N	\N	Kvkvkkv	ckckvkc	kfkckcl	övlvçvç	lvlvlv	2	4
59	0	1	https://img.icons8.com/color/96/000000/rain.png	string	string	string	string	string	string	1	1
60	3	3	\N	\N	string	string	string	string	string	2	5
61	1	3	\N	\N	What is in the picture?	Sea	Fish	Desk	Ship	1	3
62	1	3	\N	\N	What is the image	Happy	Sad	Angry	Sea	1	2
63	1	3	\N	\N	Dhfn	rhrj	fjrh	dhfj	djtj	1	1
64	1	3	\N	\N	What is in the picture	Happy	Sad	Angry	Excited	1	3
65	1	3	\N	\N	What is in the picture	Sad	Happy	Angry	Cow	2	3
66	1	3	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/exercise/66.jpeg	\N	xxxx	lsnd	kdjd	jdkd	nnd	1	2
67	1	4	\N	\N	_______ to London on train yesterday?	Did Mary went	Did Mary go	Mary go	Mary goes	2	5
68	1	4	https://kereviz-upload.s3.eu-central-1.amazonaws.com/image/exercise/68.jpeg	\N	_______ to London on train yesterday?	Did Mary went	Did Mary go	Mary go	Mary goes	2	5
\.


--
-- Data for Name: languages; Type: TABLE DATA; Schema: proseidon; Owner: bounswe9
--

COPY proseidon.languages (id, name) FROM stdin;
1	English
2	Turkish
3	Italian
\.


--
-- Data for Name: tags; Type: TABLE DATA; Schema: proseidon; Owner: bounswe9
--

COPY proseidon.tags (id, exercise_id, tag_text) FROM stdin;
4	13	lazy
3	13	video
5	51	tennis
6	13	tv
7	13	television
8	12	test
\.


--
-- Data for Name: conversations; Type: TABLE DATA; Schema: raven; Owner: bounswe9
--

COPY raven.conversations (id, user_id_one, user_id_two, last_updated_at) FROM stdin;
1	1	3	2019-11-13 00:51:05.28
3	1	4	2019-11-10 22:07:54.346
2	2	1	2019-11-24 08:21:56.034
4	33	34	2019-11-24 19:59:33.284
5	9	2	2019-11-24 20:09:54.875
6	35	36	2019-11-25 04:34:05.597
10	42	23	2019-11-26 12:11:50.382
11	5	1	2019-12-01 18:08:05.896
7	9	8	2019-12-01 19:20:34.554
8	37	9	2019-12-20 18:59:15.925
12	39	48	2019-12-23 20:50:14.037
9	39	30	2019-12-23 20:50:26.329
13	41	22	2019-12-24 13:33:39.146
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: raven; Owner: bounswe9
--

COPY raven.messages (id, source_id, receiver_id, content, created_at) FROM stdin;
1	2	1	test	2019-11-09 19:09:24.556
2	2	1	test	2019-11-09 19:10:59.737
3	20	1	test	2019-11-09 19:13:54.413
4	20	1	test	2019-11-09 19:20:30.993
5	20	1	test	2019-11-09 19:25:57.275
6	1	3	message test	2019-11-13 00:50:46.699
7	1	3	message tessst	2019-11-13 00:51:05.28
8	1	2	asdfghjkl	2019-11-24 08:21:25.527
9	1	2	asdfghjkl	2019-11-24 08:21:56.034
10	9	2	selam	2019-11-24 17:49:07.09
11	2	9	selam	2019-11-24 17:49:19.48
12	2	9	nabber	2019-11-24 17:49:28.963
13	2	9	nabber	2019-11-24 17:49:30.561
14	2	9	nabber	2019-11-24 17:49:31.344
15	2	9	nabber	2019-11-24 18:55:51.799
16	9	2	asffghff	2019-11-24 19:01:23.69
17	9	2	asdfghjkli	2019-11-24 19:01:34.912
18	9	2	ibrahm	2019-11-24 19:01:46.86
19	9	2	iyi	2019-11-24 19:18:16.6
20	9	2	çok güzel mesajlaşma	2019-11-24 19:56:26.617
21	9	2	yfgjomö	2019-11-24 20:02:18.924
22	9	2	irem	2019-11-24 20:09:54.875
23	37	9	Selam İremm 	2019-11-25 07:51:11.692
24	37	9	Deneme yapıyorum :D 	2019-11-25 07:51:39.463
25	9	37	selam 	2019-11-25 07:53:31.176
26	37	9	Günaydın irem	2019-11-25 13:39:09.81
27	37	9	Ben de ibrahim :D	2019-11-25 13:39:16.587
28	9	37	Ben de game :D\n	2019-11-25 13:39:34.911
29	37	9	Ama saat sıkıntılı :D	2019-11-25 13:40:12.159
30	37	9	13 40 yazıyor	2019-11-25 13:40:24.313
31	9	8	selam gg	2019-11-25 13:40:30.933
32	37	9	Halbuki 16 40	2019-11-25 13:40:36.387
33	9	8	deneme	2019-11-25 20:17:43.781
34	30	39	asd	2019-11-26 02:57:02.053
35	30	39	Kaja	2019-11-26 02:57:25.563
36	39	30	Heyo	2019-11-26 02:58:14.29
37	30	39	DOMATES	2019-11-26 03:18:51.164
38	42	23	Hola amigo	2019-11-26 10:51:06.525
39	23	42	Hello buddy	2019-11-26 12:10:56.128
40	42	23	How are you buddy	2019-11-26 12:11:17.714
41	23	42	Fine, presenting	2019-11-26 12:11:50.382
42	8	9	this is a message	2019-12-01 19:20:34.554
43	37	9	selam irem \nncjdnkj\njkkdkdkdk\nkdkdkkfkf	2019-12-20 18:59:15.925
44	30	39	ghj	2019-12-22 13:52:37.316
45	30	39	yuj	2019-12-22 13:52:57.332
46	30	39	Does chat work?	2019-12-22 13:54:06.352
47	30	39	I think so?	2019-12-22 13:55:04.352
48	30	39	I think so	2019-12-22 13:55:14.49
49	30	39	Bullets overflow over input boxname and ...ago clashes, af	2019-12-22 13:57:44.487
50	30	39	Bullets overflow over input box	2019-12-22 13:58:00.635
51	30	39	name and ...ago clashes	2019-12-22 13:58:11.386
52	30	39	After send, input box does not clean itself	2019-12-22 13:58:28.25
53	30	39	ugh	2019-12-22 13:58:36.528
54	30	39	input box acts like it belongs to the background, does not respond to scroll	2019-12-22 14:08:23.324
55	30	39	1	2019-12-22 14:08:45.119
56	30	39	2	2019-12-22 14:08:46.864
57	30	39	3	2019-12-22 14:08:48.573
58	30	39	4	2019-12-22 14:08:49.943
59	30	39	5	2019-12-22 14:08:51.633
60	30	39	6	2019-12-22 14:08:55.121
61	30	39	7	2019-12-22 14:08:57.196
62	30	39	asd	2019-12-23 18:01:17.073
63	30	39	domates	2019-12-23 18:04:34.963
64	30	39	asd	2019-12-23 18:05:00.06
65	30	39	asd	2019-12-23 18:05:27.188
66	30	39	adsd	2019-12-23 18:06:55.145
67	30	39	adsda	2019-12-23 18:07:01.411
68	30	39	asda	2019-12-23 18:09:12.163
69	30	39	asds	2019-12-23 18:11:11.098
70	30	39	asds	2019-12-23 18:11:41.998
71	30	39	asdsad	2019-12-23 18:14:06.485
72	30	39	asdsa	2019-12-23 18:14:11.702
73	30	39	asdsda	2019-12-23 18:14:14.347
74	30	39	asdas	2019-12-23 18:15:42.968
75	30	39	asdas	2019-12-23 18:16:23.054
76	30	39	asdasd	2019-12-23 18:16:40.828
77	30	39	asdas	2019-12-23 18:17:02.489
78	30	39	asdsa	2019-12-23 18:17:50.999
79	30	39	zzz	2019-12-23 18:18:33.24
80	30	39	zzz	2019-12-23 18:18:52.468
81	30	39	asds	2019-12-23 18:19:59.378
82	30	39	dasdasd	2019-12-23 18:20:07.892
83	30	39	sadsadas	2019-12-23 18:20:56.197
84	30	39	adsdasd	2019-12-23 18:20:58.201
85	30	39	asdasdas	2019-12-23 18:21:09.091
86	30	39	sdad	2019-12-23 18:21:14.377
87	30	39	asdsadasd	2019-12-23 18:22:23.991
88	30	39	asdsad	2019-12-23 18:22:38.94
89	30	39	ıuewr	2019-12-23 18:25:19.384
90	30	39	qwe	2019-12-23 18:37:47.017
91	30	39	qwert	2019-12-23 18:38:06.674
92	30	39	adwd	2019-12-23 18:50:08.39
93	30	39	adwd	2019-12-23 18:50:27.9
94	30	39	adwd	2019-12-23 18:50:41.271
95	30	39	as	2019-12-23 18:51:29.909
96	30	39	asasd	2019-12-23 18:53:52.628
97	30	39	qwe	2019-12-23 18:55:00.287
98	30	39	qwewe	2019-12-23 18:55:03.595
99	30	39	qwewe	2019-12-23 18:55:21.497
100	30	39	asd	2019-12-23 18:56:29.414
101	30	39	asd	2019-12-23 18:57:20.901
102	30	39	asdw	2019-12-23 19:01:07.178
103	30	39	aa	2019-12-23 19:07:00.797
104	30	39	a	2019-12-23 19:20:04.9
105	30	39	asdsd	2019-12-23 19:26:59.601
106	39	30	askdjh	2019-12-23 20:50:26.329
107	41	22	merhaba mahmut naber	2019-12-24 13:33:28.991
108	41	22	buraya geldiğine ok sevindim	2019-12-24 13:33:39.146
\.


--
-- Name: annotations_id_seq; Type: SEQUENCE SET; Schema: actor; Owner: bounswe9
--

SELECT pg_catalog.setval('actor.annotations_id_seq', 38, true);


--
-- Name: grades_id_seq; Type: SEQUENCE SET; Schema: actor; Owner: bounswe9
--

SELECT pg_catalog.setval('actor.grades_id_seq', 50, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: actor; Owner: bounswe9
--

SELECT pg_catalog.setval('actor.hibernate_sequence', 1, false);


--
-- Name: invitations_id_seq; Type: SEQUENCE SET; Schema: actor; Owner: bounswe9
--

SELECT pg_catalog.setval('actor.invitations_id_seq', 22, true);


--
-- Name: solved_exercises_id_seq; Type: SEQUENCE SET; Schema: actor; Owner: bounswe9
--

SELECT pg_catalog.setval('actor.solved_exercises_id_seq', 107, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: actor; Owner: bounswe9
--

SELECT pg_catalog.setval('actor.users_id_seq', 53, true);


--
-- Name: assignments_id_seq; Type: SEQUENCE SET; Schema: mahmuthoca; Owner: bounswe9
--

SELECT pg_catalog.setval('mahmuthoca.assignments_id_seq', 9, true);


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: mahmuthoca; Owner: bounswe9
--

SELECT pg_catalog.setval('mahmuthoca.comments_id_seq', 17, true);


--
-- Name: essays_id_seq; Type: SEQUENCE SET; Schema: mahmuthoca; Owner: bounswe9
--

SELECT pg_catalog.setval('mahmuthoca.essays_id_seq', 24, true);


--
-- Name: ratings_id_seq; Type: SEQUENCE SET; Schema: mahmuthoca; Owner: bounswe9
--

SELECT pg_catalog.setval('mahmuthoca.ratings_id_seq', 17, true);


--
-- Name: requests_id_seq; Type: SEQUENCE SET; Schema: mahmuthoca; Owner: bounswe9
--

SELECT pg_catalog.setval('mahmuthoca.requests_id_seq', 54, true);


--
-- Name: exercises_id_seq; Type: SEQUENCE SET; Schema: proseidon; Owner: bounswe9
--

SELECT pg_catalog.setval('proseidon.exercises_id_seq', 68, true);


--
-- Name: languages_id_seq; Type: SEQUENCE SET; Schema: proseidon; Owner: bounswe9
--

SELECT pg_catalog.setval('proseidon.languages_id_seq', 3, true);


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: proseidon; Owner: bounswe9
--

SELECT pg_catalog.setval('proseidon.tags_id_seq', 8, true);


--
-- Name: conversations_id_seq; Type: SEQUENCE SET; Schema: raven; Owner: bounswe9
--

SELECT pg_catalog.setval('raven.conversations_id_seq', 13, true);


--
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: raven; Owner: bounswe9
--

SELECT pg_catalog.setval('raven.messages_id_seq', 108, true);


--
-- Name: annotations annotations_pk; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.annotations
    ADD CONSTRAINT annotations_pk PRIMARY KEY (id);


--
-- Name: assignments assignments_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.assignments
    ADD CONSTRAINT assignments_pkey PRIMARY KEY (id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: conversations conversations_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.conversations
    ADD CONSTRAINT conversations_pkey PRIMARY KEY (id);


--
-- Name: essays essays_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.essays
    ADD CONSTRAINT essays_pkey PRIMARY KEY (id);


--
-- Name: exercises exercises_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.exercises
    ADD CONSTRAINT exercises_pkey PRIMARY KEY (id);


--
-- Name: grades grades_pk; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.grades
    ADD CONSTRAINT grades_pk PRIMARY KEY (id);


--
-- Name: invitations invitations_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.invitations
    ADD CONSTRAINT invitations_pkey PRIMARY KEY (id);


--
-- Name: languages languages_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.languages
    ADD CONSTRAINT languages_pkey PRIMARY KEY (id);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: ratings ratings_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);


--
-- Name: requests requests_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.requests
    ADD CONSTRAINT requests_pkey PRIMARY KEY (id);


--
-- Name: solved_exercises solved_exercises_pk; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.solved_exercises
    ADD CONSTRAINT solved_exercises_pk PRIMARY KEY (id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: languages uk_f6axmaokhmrbmm746866v0uyu; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.languages
    ADD CONSTRAINT uk_f6axmaokhmrbmm746866v0uyu UNIQUE (name);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: actor; Owner: bounswe9
--

ALTER TABLE ONLY actor.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: assignments assignments_pk; Type: CONSTRAINT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.assignments
    ADD CONSTRAINT assignments_pk PRIMARY KEY (id);


--
-- Name: comments comments_pk; Type: CONSTRAINT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.comments
    ADD CONSTRAINT comments_pk PRIMARY KEY (id);


--
-- Name: essays essays_pk; Type: CONSTRAINT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.essays
    ADD CONSTRAINT essays_pk PRIMARY KEY (id);


--
-- Name: ratings ratings_pk; Type: CONSTRAINT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.ratings
    ADD CONSTRAINT ratings_pk PRIMARY KEY (id);


--
-- Name: requests requests_pk; Type: CONSTRAINT; Schema: mahmuthoca; Owner: bounswe9
--

ALTER TABLE ONLY mahmuthoca.requests
    ADD CONSTRAINT requests_pk PRIMARY KEY (id);


--
-- Name: exercises exercises_pk; Type: CONSTRAINT; Schema: proseidon; Owner: bounswe9
--

ALTER TABLE ONLY proseidon.exercises
    ADD CONSTRAINT exercises_pk PRIMARY KEY (id);


--
-- Name: languages languages_pk; Type: CONSTRAINT; Schema: proseidon; Owner: bounswe9
--

ALTER TABLE ONLY proseidon.languages
    ADD CONSTRAINT languages_pk PRIMARY KEY (id);


--
-- Name: tags tags_pk; Type: CONSTRAINT; Schema: proseidon; Owner: bounswe9
--

ALTER TABLE ONLY proseidon.tags
    ADD CONSTRAINT tags_pk PRIMARY KEY (id);


--
-- Name: conversations conversations_pk; Type: CONSTRAINT; Schema: raven; Owner: bounswe9
--

ALTER TABLE ONLY raven.conversations
    ADD CONSTRAINT conversations_pk PRIMARY KEY (id);


--
-- Name: messages messages_pk; Type: CONSTRAINT; Schema: raven; Owner: bounswe9
--

ALTER TABLE ONLY raven.messages
    ADD CONSTRAINT messages_pk PRIMARY KEY (id);


--
-- Name: annotations_id_uindex; Type: INDEX; Schema: actor; Owner: bounswe9
--

CREATE UNIQUE INDEX annotations_id_uindex ON actor.annotations USING btree (id);


--
-- Name: grades_id_uindex; Type: INDEX; Schema: actor; Owner: bounswe9
--

CREATE UNIQUE INDEX grades_id_uindex ON actor.grades USING btree (id);


--
-- Name: solved_exercises_id_uindex; Type: INDEX; Schema: actor; Owner: bounswe9
--

CREATE UNIQUE INDEX solved_exercises_id_uindex ON actor.solved_exercises USING btree (id);


--
-- Name: users_email_uindex; Type: INDEX; Schema: actor; Owner: bounswe9
--

CREATE UNIQUE INDEX users_email_uindex ON actor.users USING btree (email);


--
-- Name: users_id_uindex; Type: INDEX; Schema: actor; Owner: bounswe9
--

CREATE UNIQUE INDEX users_id_uindex ON actor.users USING btree (id);


--
-- Name: assignments_id_uindex; Type: INDEX; Schema: mahmuthoca; Owner: bounswe9
--

CREATE UNIQUE INDEX assignments_id_uindex ON mahmuthoca.assignments USING btree (id);


--
-- Name: comments_id_uindex; Type: INDEX; Schema: mahmuthoca; Owner: bounswe9
--

CREATE UNIQUE INDEX comments_id_uindex ON mahmuthoca.comments USING btree (id);


--
-- Name: essays_id_uindex; Type: INDEX; Schema: mahmuthoca; Owner: bounswe9
--

CREATE UNIQUE INDEX essays_id_uindex ON mahmuthoca.essays USING btree (id);


--
-- Name: ratings_id_uindex; Type: INDEX; Schema: mahmuthoca; Owner: bounswe9
--

CREATE UNIQUE INDEX ratings_id_uindex ON mahmuthoca.ratings USING btree (id);


--
-- Name: requests_id_uindex; Type: INDEX; Schema: mahmuthoca; Owner: bounswe9
--

CREATE UNIQUE INDEX requests_id_uindex ON mahmuthoca.requests USING btree (id);


--
-- Name: exercises_id_uindex; Type: INDEX; Schema: proseidon; Owner: bounswe9
--

CREATE UNIQUE INDEX exercises_id_uindex ON proseidon.exercises USING btree (id);


--
-- Name: languages_id_uindex; Type: INDEX; Schema: proseidon; Owner: bounswe9
--

CREATE UNIQUE INDEX languages_id_uindex ON proseidon.languages USING btree (id);


--
-- Name: languages_name_uindex; Type: INDEX; Schema: proseidon; Owner: bounswe9
--

CREATE UNIQUE INDEX languages_name_uindex ON proseidon.languages USING btree (name);


--
-- Name: tags_id_uindex; Type: INDEX; Schema: proseidon; Owner: bounswe9
--

CREATE UNIQUE INDEX tags_id_uindex ON proseidon.tags USING btree (id);


--
-- Name: conversations_id_uindex; Type: INDEX; Schema: raven; Owner: bounswe9
--

CREATE UNIQUE INDEX conversations_id_uindex ON raven.conversations USING btree (id);


--
-- Name: messages_id_uindex; Type: INDEX; Schema: raven; Owner: bounswe9
--

CREATE UNIQUE INDEX messages_id_uindex ON raven.messages USING btree (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: bounswe9
--

REVOKE ALL ON SCHEMA public FROM rdsadmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO bounswe9;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

