create table genre
(
	name varchar(50) charset utf8 not null
		primary key
);

create table name
(
	id varchar(10) charset utf8 not null
		primary key,
	primary_name varchar(500) charset utf8 null,
	birth_year smallint(4) null,
	death_year smallint(4) null,
	primary_profession varchar(500) charset utf8 null
);

create index name_primary_name_index
	on name (primary_name);

create table name_basics_original
(
	name_id varchar(10) charset utf8 null,
	primaryName varchar(500) charset utf8 null,
	birthYear smallint(4) null,
	deathYear smallint(4) null,
	primaryProfession varchar(500) charset utf8 null,
	knownForTitles varchar(500) charset utf8 null
);

create table principal_t
(
	id int not null
		primary key,
	title_id varchar(10) charset utf8 not null,
	ordering int null,
	name_id varchar(10) charset utf8 null,
	category varchar(200) charset utf8 null,
	job varchar(200) charset utf8 null,
	characters varchar(500) charset utf8 null
);

create table title_aka_original
(
	titleId varchar(10) charset utf8 null,
	ordering int null,
	title varchar(500) charset utf8 null,
	region varchar(50) charset utf8 null,
	language varchar(50) charset utf8 null,
	types varchar(500) charset utf8 null,
	attributes varchar(500) charset utf8 null,
	isOriginalTitle tinyint(1) null
);

create table title_basics_original
(
	id varchar(10) charset utf8 not null
		primary key,
	titleType varchar(50) charset utf8 null,
	primaryTitle varchar(500) charset utf8 null,
	originalTitle varchar(500) charset utf8 null,
	isAdult tinyint(1) null,
	startYear smallint(4) null,
	endYear smallint(4) null,
	runtimeMinutes smallint(4) null,
	genres varchar(500) charset utf8 null
);

create table title_crew
(
	title_id varchar(10) charset utf8 null,
	directors varchar(500) charset utf8 null,
	writers varchar(500) charset utf8 null
);

create table title_episode_original
(
	id varchar(10) charset utf8 null,
	parent_id varchar(10) charset utf8 null,
	seasonNumber smallint(4) null,
	episodeNumber smallint(4) null
);

create table title_principals_original
(
	title_id varchar(10) charset utf8 null,
	ordering int null,
	name_id varchar(10) charset utf8 null,
	category varchar(200) charset utf8 null,
	job varchar(200) charset utf8 null,
	characters varchar(500) charset utf8 null
);

create table title_ratings_original
(
	title_id varchar(10) charset utf8 not null
		primary key,
	averageRating float null,
	numVotes int null
);

create table title_type
(
	name varchar(50) charset utf8 not null
		primary key
);

create table title
(
	id varchar(10) charset utf8 not null
		primary key,
	title_type varchar(50) charset utf8 null,
	primary_title varchar(500) charset utf8 null,
	original_title varchar(500) charset utf8 null,
	is_adult tinyint(1) null,
	start_year smallint(4) null,
	end_year smallint(4) null,
	runtime_minutes smallint(4) null,
	average_rating float null,
	number_of_vote int null,
	constraint title_title_type_name_fk
		foreign key (title_type) references title_type (name)
);

create table episode
(
	id varchar(10) charset utf8 not null
		primary key,
	parent_id varchar(10) charset utf8 null,
	season_number int(4) null,
	episode_number int(4) null,
	constraint episode_title_id_fk
		foreign key (parent_id) references title (id)
);

create index episode_parent_id_index
	on episode (parent_id);

create table known_for_title
(
	title_id varchar(10) charset utf8 not null,
	name_id varchar(10) charset utf8 not null,
	constraint known_for_title_title_id_name_id_uindex
		unique (title_id, name_id),
	constraint known_for_title_name_id_fk
		foreign key (name_id) references name (id),
	constraint known_for_title_title_id_fk
		foreign key (title_id) references title (id)
);

create index known_for_title_name_id_index
	on known_for_title (name_id);

create index known_for_title_title_id_index
	on known_for_title (title_id);

create table principal
(
	id int auto_increment
		primary key,
	title_id varchar(10) charset utf8 not null,
	ordering int null,
	name_id varchar(10) charset utf8 null,
	category varchar(200) charset utf8 null,
	job varchar(200) charset utf8 null,
	characters varchar(500) charset utf8 null,
	constraint principal_name_id_title_id_uindex
		unique (name_id, title_id),
	constraint principal_name_id_fk
		foreign key (name_id) references name (id),
	constraint principal_title_id_fk
		foreign key (title_id) references title (id)
);

create index principal_name_id_index
	on principal (name_id);

create index principal_title_id_index
	on principal (title_id);

create index title_average_rating_index
	on title (average_rating);

create index title_average_rating_number_of_vote_index
	on title (average_rating, number_of_vote);

create index title_number_of_vote_index
	on title (number_of_vote);

create index title_original_title_index
	on title (original_title);

create index title_primary_title_index
	on title (primary_title);

create table title_aka
(
	id int auto_increment
		primary key,
	title_id varchar(10) charset utf8 not null,
	ordering int null,
	title varchar(500) charset utf8 null,
	region varchar(50) charset utf8 null,
	language varchar(100) charset utf8 null,
	attributes varchar(500) charset utf8 null,
	is_original_title tinyint(1) null,
	constraint title_aka_title_id_fk
		foreign key (title_id) references title (id)
);

create index title_aka_title_id_index
	on title_aka (title_id);

create table title_director
(
	title_id varchar(10) charset utf8 not null,
	director_id varchar(10) charset utf8 not null,
	constraint title_director_title_id_director_id_uindex
		unique (title_id, director_id),
	constraint title_director_name_id_fk
		foreign key (director_id) references name (id),
	constraint title_director_title_id_fk
		foreign key (title_id) references title (id)
);

create index title_director_director_id_index
	on title_director (director_id);

create index title_director_title_id_index
	on title_director (title_id);

create table title_genre
(
	title_id varchar(10) charset utf8 not null,
	genre_id varchar(50) charset utf8 not null,
	constraint title_genre_genre_name_fk
		foreign key (genre_id) references genre (name),
	constraint title_genre_title_id_fk
		foreign key (title_id) references title (id)
);

create index title_genre_genre_id_index
	on title_genre (genre_id);

create index title_genre_title_id_index
	on title_genre (title_id);

create table title_writer
(
	title_id varchar(10) charset utf8 null,
	writer_id varchar(10) charset utf8 null,
	constraint title_writer_name_id_fk
		foreign key (writer_id) references name (id),
	constraint title_writer_title_id_fk
		foreign key (title_id) references title (id)
);

create index title_writer_title_id_index
	on title_writer (title_id);

create index title_writer_writer_id_index
	on title_writer (writer_id);