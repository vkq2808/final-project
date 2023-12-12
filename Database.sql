create database FinalProjectWeb
go 

use FinalProjectWeb
go

--Tạo bảng User
	CREATE TABLE Users 
	(
		UserID BIGINT IDENTITY (1,1) NOT NULL,
		UserName NVARCHAR(100) NULL DEFAULT NULL,
		NumPhone VARCHAR(12) UNIQUE NULL,
		Email VARCHAR(50) UNIQUE NULL,
		[Password] VARCHAR(24) NOT NULL,
		Avatar VARCHAR(1000) NULL DEFAULT NULL,
		IsAdmin BIT NOT NULL,
		CONSTRAINT PK_Users PRIMARY KEY (UserID)
	);
	go



-- Bản Post
CREATE TABLE Post
(
    PostID BIGINT IDENTITY(1,1) NOT NULL,
    UserID BIGINT NOT NULL,
    Content NVARCHAR(2048) NULL DEFAULT NULL,
    CreatedAt DATETIME2 NOT NULL,
    UpdatedAt DATETIME2 NULL DEFAULT NULL,
    CONSTRAINT PK_Post PRIMARY KEY NONCLUSTERED (PostID), -- Thêm khóa chính
    INDEX IX_Post_UserID NONCLUSTERED ([UserID]), -- Thêm chỉ mục cho UserID
    CONSTRAINT FK_Post_User FOREIGN KEY ([UserID]) REFERENCES [Users]([UserId]) ON DELETE NO ACTION ON UPDATE NO ACTION
);
go

--Bảng Comment
CREATE TABLE Comment(
  CommentID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  UserID BIGINT NOT NULL,
  PostID BIGINT NOT NULL,
  Content NVARCHAR(2048) NULL DEFAULT NULL,
  CreatedAt DATETIME2 NOT NULL,
  UpdatedAt DATETIME2 NULL DEFAULT NULL,
  FOREIGN KEY (UserID) REFERENCES [Users](UserID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (PostID) REFERENCES [Post](PostID) ON DELETE NO ACTION ON UPDATE NO ACTION
);
go

--Bảng Like
CREATE TABLE Likes
(
  LikeID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  UserId BIGINT NOT NULL,
  PostID BIGINT NOT NULL,
  CreatedAt DATETIME2 NOT NULL,
  UpdatedAt DATETIME2 NULL DEFAULT NULL,
  FOREIGN KEY ([UserID]) REFERENCES [Users]([UserID]) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (PostID) REFERENCES [Post](PostID) ON DELETE NO ACTION ON UPDATE NO ACTION
);
go

-- Bảng Messfolder
CREATE TABLE MessageFolder 
(
  MessageFolderID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  MessageFolderName nvarchar(200) NULL DEFAULT NULL,
  MessageFolderAvatarLink VARCHAR(2048) NULL DEFAULT NULL,
  LatestMessageID BIGINT NULL DEFAULT NULL,
  CreatedAt datetime
);
go

--Bảng Message
CREATE TABLE [Messages] (
  MessageID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  SenderID BIGINT NOT NULL,
  MessageFolderID BIGINT NOT NULL,
  Content NVARCHAR(2048) NULL DEFAULT NULL,
  CreatedAt DATETIME2 NOT NULL,
  FOREIGN KEY (SenderID) REFERENCES [Users]([UserID]) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (MessageFolderID) REFERENCES Messagefolder(MessageFolderID) ON DELETE NO ACTION ON UPDATE NO ACTION
);
go


--Bảng Calling
CREATE TABLE Calling (
  ConverID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  CallerID BIGINT NOT NULL,
  ReceiverID BIGINT NOT NULL,
  Created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
  deleted_at DATETIME2 NULL,
  FOREIGN KEY (CallerID) REFERENCES [Users](UserID) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (ReceiverID) REFERENCES [Users](UserID) ON DELETE NO ACTION ON UPDATE NO ACTION
);
go

--Bảng Participants
CREATE TABLE Participants (
  ParID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  MessageFolderID BIGINT,
  UserID BIGINT 
  FOREIGN KEY (MessageFolderID) REFERENCES MessageFolder(MessageFolderID) ON DELETE NO ACTION ON UPDATE NO ACTION
);
go

CREATE UNIQUE INDEX participants_UNIQUE ON Participants (
  MessageFolderID,
  UserID
);
go

CREATE TABLE [dbo].[Relationship](
	[RelationshipID] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[FromUserID] [bigint] NOT NULL,
	[ToUserID] [bigint] NOT NULL,
	[isFriend] [bit] NOT NULL DEFAULT 0,
	[isBlocking] [bit] NOT NULL DEFAULT 0,
	[isPending] [bit] NOT NULL DEFAULT 0,
	FOREIGN KEY ([FromUserID]) REFERENCES Users(UserID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY ([ToUserID]) REFERENCES Users(UserID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ON [PRIMARY]
GO

CREATE UNIQUE INDEX OnlyOneRelationEachPair_UNIQUE ON Relationship (
  [FromUserID],
  [ToUserID]
);
go

-- ImageLink dùng cho cả Post, Comment và Message
CREATE TABLE ImageLink
(
    ImageLinkID BIGINT IDENTITY (1,1) NOT NULL,
    PostID BIGINT NULL DEFAULT NULL,
	CommentID BIGINT NULL DEFAULT NULL,
	MessageID BIGINT NULL DEFAULT NULL,
    ImageLink VARCHAR(2048) NOT NULL,
    CONSTRAINT PK_PostImageLink PRIMARY KEY NONCLUSTERED (ImageLinkID),
    CONSTRAINT FK_ImageLink_Post FOREIGN KEY (PostID) REFERENCES Post(PostID) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT FK_ImageLink_Comment FOREIGN KEY (CommentID) REFERENCES Comment(CommentID) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT FK_ImageLink_Message FOREIGN KEY (MessageID) REFERENCES [Messages](MessageID) ON DELETE CASCADE ON UPDATE NO ACTION,
);
go


--Xóa tất cả dử liệu

DBCC CHECKIDENT (Users, RESEED, 1);
go
DBCC CHECKIDENT (Calling, RESEED, 1);
go
DBCC CHECKIDENT (Comment, RESEED, 1);
go
DBCC CHECKIDENT (ImageLink, RESEED, 1);
go
DBCC CHECKIDENT (Likes, RESEED, 1);
go
DBCC CHECKIDENT (MessageFolder, RESEED, 1);
go
DBCC CHECKIDENT ([Messages], RESEED, 1);
go
DBCC CHECKIDENT (Participants, RESEED, 1);
go
DBCC CHECKIDENT (Post, RESEED, 1);
go
DBCC CHECKIDENT (Relationship, RESEED, 1);
go


--Dữ liệu demo
INSERT INTO [dbo].[Users] ([UserName],[NumPhone],[Email],[Password],[Avatar],IsAdmin)
     VALUES
			(N'Vũ Khánh Quốc','0919309031', 'vkq265@gmail.com','quoc123456','https://i.imgur.com/yib8dhZ.jpg',1),
			(N'Trần Vĩnh Hùng','0943253097', 'hung@gmail.com','hung123456',NULL,0),
			(N'Lê Nguyễn Trí Nhân','0345001422', 'nhan@gmail.com','nhan123456','https://i.imgur.com/jf621ot.jpg',0),
			(N'Trình Học Tuấn','0919859633', 'tuan@gmail.com','tuan123456','https://i.imgur.com/FTW4qYJ.jpeg',0),
			(N'Nguyễn Việt An','0919128534', 'an@gmail.com','an123456',NULL,0),
			(N'Ngô Hoàng Ân','0919142535', 'anngo@gmail.com','tuan123456',NULL,0),
			(N'Tran Thị Á Tiên','0919124736', 'tien@gmail.com','tien123456',NULL,0),
			(N'Pham Quốc Phương Đông','0998968704', 'dong@gmail.com','dong123456',NULL,0),
			(N'Nguyễn Thị Xuân Mai','0965120156', 'mai@gmail.com','mai26062003',NULL,0),
			(N'Đào Duy Phát','0984963500', 'phat@gmail.com','phat123456',NULL,0),
			(N'Lê Nguyễn Thiên Tứ','0960035655', 'tu@gmail.com','tu123456',NULL,0),
			(N'Nguyen Quang Vinh','0936167372', 'vinh@gmail.com','vinh123456','https://i.imgur.com/uDHrLr6.jpg',0),
			(N'Tào Chí Vỹ','0974945002', 'vytao@gmail.com','taovy1234566','https://i.imgur.com/TMEV4Hc.jpg',0),
			(N'Phan Thị Kim Oanh','0975830797', 'oanhphan@gmail.com','oanhkute','https://i.imgur.com/8q0qXSV.jpeg',0),
			(N'La Thị Huỳnh Hoa','0939790100', 'lthh0307@gmail.com','lthh0307','https://i.imgur.com/jskJfOn.jpg',0),
			(N'Nguyễn Thị Thiên Phúc','0923967944', 'phucnguyen@gmail.com','thienphuc23','https://i.imgur.com/owJ3Bax.jpg',0),
			(N'Nguyễn Thị Thanh Ngân Ngân','0975958000', 'nganthanhn@gmail.com','nganthanh123','https://i.imgur.com/KUERFHK.jpg',0),
			(N'Nguyễn Lê Trà Giang','0915844522', 'giangnguyen@gmail.com','giangnguyen123','https://i.imgur.com/7zTQdFz.jpg',0),
			(N'Nguyễn Song Minh Tâm','0999154533', 'tamnguyen@gmail.com','songtam123',NULL,0),
			(N'Đặng Hoàng Toàn','0955441112', 'toan@gmail.com','toan123456',NULL,0);

GO

INSERT INTO [dbo].[Post] ([UserID],[Content],[CreatedAt],[UpdatedAt])
     VALUES
           (1,N'Trí Nhân bị bede','2023-11-30 21:00:00.0000000',NULL),
		   (2,N'Cần tìm một anh đẹp trai','2023-11-30 22:00:00.0000000',NULL),
		   (15,N'Đây là tôi thời học sinh','2023-12-01 08:00:00.0000000',NULL),
		   (2,N'Nhớ người yêu cũ quá :(','2023-12-02 00:00:00.0000000',NULL),
		   (13,N'Một ngày quá mệt mỏi','2023-12-03 16:00:00.0000000',NULL),
		   (4,N'Chill chill chạy deadline!','2023-11-30 00:00:00.0000000',NULL),
		   (4,N'Đi làm tóc thui nào','2023-12-04 09:00:00.0000000',NULL),
		   (3,N'Gặp được mấy thằng bạn khi đi học nek trời!','2023-12-04 10:00:00.0000000',NULL);
GO
INSERT INTO [dbo].[Comment]  ([UserID],[PostID],[Content],[CreatedAt],[UpdatedAt])
     VALUES
		(2,1,N'Đúng đúng đúng','2023-11-30 21:05:00.0000000',NULL),
		(3,1,N'Lộ hết rùi','2023-11-30 11:00:06.0000000',NULL),
		(1,2,N'Học Tuấn, Trí Nhân đâu hết rồi','2023-11-30 22:05:00.0000000',NULL),
		(4,2,N'Để anh','2023-11-30 22:00:00.0000000',NULL);
GO

INSERT INTO [dbo].[Likes]   ([UserId],[PostID],[CreatedAt])
     VALUES
		(2,1,'2023-11-30 01:00:00.000000'),
		(3,1,'2023-11-30 01:00:00.000000'),
		(1,2,'2023-11-30 01:00:00.000000'),
		(3,2,'2023-11-30 01:00:00.000000');
GO

INSERT INTO dbo.ImageLink (PostID,ImageLink)
	VALUES
		(1,'https://scontent-hkg4-1.xx.fbcdn.net/v/t39.30808-6/283316572_673764547021162_7171899460807601694_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=9c7eae&_nc_eui2=AeF_YNjlR2kRWMBLG-LIqJhslUw-tNP6gk-VTD600_qCT4_XYKJ7R_AwoUoKh-slUHG_rsUT5UOTVCfiAIUvQw3w&_nc_ohc=Qpt03xCXhZQAX_-V8Yl&_nc_ht=scontent-hkg4-1.xx&oh=00_AfDgkPETGqbAGujcAcx7pB6ncWnAglVtEScl3Imm6yB2ng&oe=657850E1'),
		(2,'https://i.redd.it/sc44m2xvruu81.jpg'),
		(3,'https://i.imgur.com/3IDKtNA.jpg'),
		(4,'https://i.imgur.com/LKRXODy.jpg'),
		(5,'https://i.imgur.com/jbgDdL5.jpg'),
		(6,'https://i.imgur.com/MVS4zP9.jpg'),
		(7,'https://i.imgur.com/Ym0b0pL.jpg'),
		(8,'https://i.imgur.com/XO6B2NX.jpg');
go

INSERT INTO dbo.Messagefolder (MessageFolderName,MessageFolderAvatarLink,LatestMessageID)
	VALUES
		(NULL,NULL,2),
		(NULL,NULL,NULL)
GO

INSERT INTO dbo.Messages (SenderID, MessageFolderID, Content, CreatedAt)
	VALUES
		(1,1,'Hello','2023-12-09 00:00:00.0000000'),
		(2,1,'I''m fine, thank you, and you?','2023-12-09 00:00:00.0000000')
INSERT INTO dbo.Participants(MessageFolderID,UserID)
	VALUES
		(1,1),
		(1,2)
INSERT INTO dbo.Relationship (FromUserID,ToUserID,isFriend,isBlocking,isPending)
	VALUES
		(1,2,1,0,0),
		(1,3,1,0,0),
		(1,4,1,0,0),
		(1,5,1,0,0),
		(2,1,1,0,0),
		(3,1,1,0,0),
		(4,1,1,0,0),
		(5,1,1,0,0)

