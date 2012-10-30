package com.brandtology.twitter;

public interface Constants {
	
	
	public static final int POSTIVE_SENTIMENT = 50;
	public static final int NEUTRAL_SENTIMENT = 0;	
	public static final int NEGATIVE_SENTIMENT = -50;
	
		
	public static String KSC5601 = "KSC5601";
	public static String UTF8 = "UTF-8";
	public static String EUCKR = "euc-kr";
	public static String MS949 = "MS949";
	public static String EUCJP = "euc-jp";
	public static String Shift_JIS = "Shift_JIS";
	public static String GBK = "GBK";
	
	public static final String ZERO = "0";
	public static final int CONN_TIME_OUT = 20000;
	public static final int HTTP_SLEEP_TIME = 2000;

	public static final String DATABASE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String TWITTER_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
	public static final String GOOGLE_DEFAULT_DATE_FORMAT = "d/M/yyyy";
	public static final String NOWTIME_DATE_FORMAT = "HH:mm:ss";

	public static final String FACEBOOK_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	public static final int ARTICLE_STATUS_NOCRAWL = 0;
	public static final int ARTICLE_STATUS_NEWCRAWL = 1;
	public static final int ARTICLE_STATUS_MONITOR = 2;
	public static final int ARTICLE_STATUS_WAITING = 3;
	public static final int ARTICLE_STATUS_CANNOT_CRAWL = 4;
	public static final int ARTICLE_STATUS_ERROR = 5;

	public static final int CHANNEL_TO_MONITOR_SEARCH_RESUALT = 10;

	public static final String STRING_COMMA = ",";
	public static final String STRING_MI = "mi";
	public static final String STRING_0MI = ".0mi";
	public static final String STRING_0KM = ".0km";
	public static final String STRING_KM = "km";
	public static final String STRING_POINT = ".";
	public static final String STRING_SPACE = "";
	public static final String STRING_COLON = ":";
	public static final String STRING_ENTER = "\n";
	public static final String STRING_TRAD = "\r";
	public static final String STRING_HTTP = "http";
	public static final String STRING_HTTPS = "https";
	public static final String STRING_REAL_TIME = "real_time";
	public static final String STRING_LEFT_SLASH = "/";
	public static final String STRING_LEFT_NEW_SLASH = "%2F";
	public static final String STRING_BRANCH = ";";
	public static final String STRING_AMP = "amp";
	public static final String STRING_ATA = "@";
	public static final String STRING_EQUAL_MARK = "=";
	public static final String STRING_ADD = "+";
	public static final String STRING_NBSP = "nbsp";
	public static final String STRING_AND = "&";
	public static final String STRING_QUESTION_MARK = "?";
	public static final String STRING_SHARP = "#";
	public static final String STRING_LEFT_ANGLE_BRACKET = "<";
	public static final String STRING_LEFT_ANGLE_BRACKET_ESCAPE = "&lt;";
	public static final String STRING_RIGHT_ANGLE_BRACKET = ">";
	public static final String STRING_RIGHT_ANGLE_BRACKET_ESCAPE = "&gt;";
	public static final String STRING_UNFIND = "UNFIND";
	public static final String STRING_TAG_A = "a";
	public static final String STRING_TAG_DIV = "div";
	public static final String STRING_TAG_TD = "td";
	public static final String STRING_TAG_LI = "li";
	public static final String STRING_TAG_H3 = "h3";
	public static final String STRING_TAG_LINK = "link";
	public static final String STRING_TAG_META = "meta";
	public static final String STRING_TAG_HTML = "html";
	public static final String STRING_TAG_TITLE = "title";
	public static final String STRING_TAG_ATTRIBUTE_HREF = "href";
	public static final String STRING_TAG_ATTRIBUTE_TYPE = "type";
	public static final String STRING_TAG_ATTRIBUTE_NAME = "name";
	public static final String STRING_TAG_ATTRIBUTE_CLASS = "class";
	public static final String STRING_TAG_ATTRIBUTE_ID = "id";
	public static final String STRING_TAG_ATTRIBUTE_TARGET = "target";
	public static final String STRING_BLOGSEARCH_FLAG = "http://blogsearch.google";
	public static final String STRING_CONFIG_DATEFORMAT_FLAG = "google.DateFormat";
	public static final String STRING_CONFIG_BLOGSEARCH_LANGUAGE_FLAG = "BlogSearch.Language";
	public static final String STRING_CONFIG_RSS_DATEFORMAT_FLAG = "rssDateFormat";
	public static final String STRING_CONFIG_NUM = "MaxThread";
	public static final String STRING_CONFIG_SITE_FLAG = "Site.Type";
	public static final String STRING_CONFIG_SITE_FILTER_FLAG = "Site.Filter";
	public static final String STRING_CHANNEL_ATTRIBUTE_COUNTRY = "country";
	public static final String STRING_CHANNEL_ATTRIBUTE_KEYWORD = "channelkeyword";
	public static final String STRING_CHANNEL_ATTRIBUTE_DATERANGE = "daterange";
	public static final String STRING_CHANNEL_ATTRIBUTE_LANGUAGE = "language";
	public static final String STRING_CHANNEL_ATTRIBUTE_SITE = "site";
	public static final String STRING_CHANNEL_ATTRIBUTE_ID = "id";
	public static final String STRING_CHANNEL_INFLUENCE_CALCULATION_ID = "Influence_Calculation_ID";
	public static final String STRING_CHANNEL_PROXY_HOST_PORT = "proxyHostPort";
	public static final String STRING_CHANNEL_PROXY_HOST = "proxyHostIP";
	public static final String STRING_CHANNEL_ATTRIBUTE_TYPE_BLOG = "BLOG";
	public static final String STRING_CHANNEL_ATTRIBUTE_CATEGORY_GENERAL = "GENERAL";
	public static final String STRING_TAG_VALUE_B = "b";
	public static final String STRING_NAVER_MAX_THREAD = "forum.naver.MaxThread";
	public static final String STRING_NAVERBLOG_MAX_THREAD = "blog.naver.MaxThread";
	public static final String STRING_NAVERQA_MAX_THREAD = "qa.naver.MaxThread";
	public static final String STRING_DAUMFORUM_MAX_THREAD = "forum.daum.MaxThread";
	public static final String STRING_DAUMBLOG_MAX_THREAD = "blog.daum.MaxThread";
	public static final String STRING_DAUMQA_MAX_THREAD = "qa.daum.MaxThread";
	public static final String STRING_EGLOOS_MAX_THREAD = "blog.egloos.MaxThread";
	public static final String STRING_TISTORY_MAX_THREAD = "blog.tistory.MaxThread";
	public static final String STRING_YAHOO_JP_MAX_THREAD = "blog.yahoo.jp.MaxThread";
	public static final String STRING_YAHOO_HK_MAX_THREAD = "blog.yahoo.hk.MaxThread";
	public static final String STRING_YAHOO_VN_MAX_THREAD = "blog.yahoo.vn.MaxThread";
	public static final String STRING_YAHOO_TW_MAX_THREAD = "blog.yahoo.tw.MaxThread";
	public static final String STRING_PLURK_MAX_THREAD = "microblog.plurk.MaxThread";
	public static final String STRING_YOUTUBE_MAX_THREAD = "video.youtube.MaxThread";
	public static final String STRING_YOUKU_MAX_THREAD = "video.youku.MaxThread";
	public static final String STRING_TUDOU_MAX_THREAD = "video.tudou.MaxThread";
	public static final String STRING_TWITTER_CLIENT = "client";
	public static final String STRING_TWITTER_CHANNEL = "channel";

	public static final String STRING_BAIDU_BLOG_MAX_THREAD = "blog.baidu.MaxThread";
	public static final String STRING_BAIDU_MICROBLOG_MAX_THREAD = "microblog.baidu.MaxThread";
	public static final String STRING_SEESAA_BLOG_MAX_THREAD = "seesaaBlog.MaxThread";
	public static final String STRING_SEESAA_ARTICLE_MAX_THREAD = "blog.seesaa.MaxThread";
	public static final String STRING_SONET_MAX_THREAD = "blog.sonet.MaxThread";
	public static final String STRING_EXBLOG_MAX_THREAD = "blog.exblog.MaxThread";
	public static final String STRING_GOO_MAX_THREAD = "blog.goo.MaxThread";
	public static final String STRING_AMEBA_MAX_THREAD = "blog.ameba.MaxThread";
	public static final String STRING_RAKUTEN_MAX_THREAD = "blog.rakuten.MaxThread";
	public static final String STRING_YAPLOG_MAX_THREAD = "blog.yaplog.MaxThread";
	public static final String STRING_DION_MAX_THREAD = "blog.dion.MaxThread";
	public static final String STRING_KAIXIN001_MAX_THREAD = "kaixin001.MaxThread";
	public static final String STRING_TEACUP_MAX_THREAD = "blog.teacup.MaxThread";
	public static final String STRING_WRETCH_MAX_THREAD = "blog.wretch.MaxThread";
	public static final String STRING_MIXI_BLOG_MAX_THREAD = "blog.mixi.MaxThread";
	public static final String STRING_163MICROBLOG_MAX_THREAD = "microblog.163.MaxThread";
	public static final String STRING_TIANYAMICROBLOG_MAX_THREAD = "microblog.tianya.MaxThread";
	public static final String STRING_IFENGMICROBLOG_MAX_THREAD = "microblog.ifeng.MaxThread";
	public static final String STRING_PEOPLEMICROBLOG_MAX_THREAD = "microblog.people.MaxThread";
	public static final String STRING_NIFTY_MAX_THREAD = "blog.nifty.MaxThread";
	public static final String STRING_GOOGLE_BLOG_MAX_THREAD = "blog.google.MaxThread";
	public static final String STRING_NIFTYMOBILE_MAX_THREAD = "niftyMobile.MaxThread";
	public static final String STRING_LIVEDOOR_MAX_THREAD = "blog.livedoor.MaxThread";
	public static final String STRING_YOKA_MAX_THREAD = "blog.yoka.MaxThread";
	public static final String STRING_PIXNET_MAX_THREAD = "blog.pixnet.MaxThread";
	public static final String STRING_MIXI_MICROBLOG_MAX_THREAD = "microblog.mixi.MaxThread";
	public static final String STRING_SINAMICROBLOG_MAX_THREAD = "microblog.sina.MaxThread";
	public static final String STRING_QQ_MICROBLOG_MAX_THREAD = "microblog.qq.MaxThread";
	public static final String STRING_QQ_BLOG_MAX_THREAD = "blog.qq.MaxThread";
	public static final String STRING_ME2DAYMICROBLOG_MAX_THREAD = "microblog.me2day.MaxThread";
	public static final String STRING_SINA_NEWS_REALTIME_MAX_THREAD = "sinaNewsRealtime.MaxThread";
	public static final String STRING_SINABLOG_MAX_THREAD = "blog.sina.MaxThread";
	public static final String STRING_CYWORLDBLOG_MAX_THREAD = "blog.cyworld.MaxThread";
	public static final String STRING_CYWORLDFORUM_MAX_THREAD = "forum.cyworld.MaxThread";
	public static final String STRING_TWITTER_MAX_THREAD = "twitterSearch.MaxThread";
	public static final String STRING_YOUDAOBLOG_MAX_THREAD = "blog.youdao.MaxThread";
	public static final String STRING_SOHUMICROBLOG_MAX_THREAD = "microblog.sohu.MaxThread";
	public static final String STRING_POCOBLOG_MAX_THREAD = "blog.poco.MaxThread";
	public static final String STRING_BLOGBUS_MAX_THREAD = "blog.blogBus.MaxThread";
	public static final String STRING_SOHU_BLOG_MAX_THREAD = "blog.sohu.MaxThread";
	public static final String STRING_FRIENDFEED_MAX_THREAD = "microblog.friendFeed.MaxThread";
	public static final String STRING_YAM_BLOG_MAX_THREAD = "blog.yam.MaxThread";
	public static final String STRING_FACEBOOK_MAX_THREAD = "facebook.MaxThread";
	public static final String STRING_GETJEALOUS_MAX_THREAD = "blog.getjealous.MaxThread";
	public static final String STRING_SGBLOG_MAX_THREAD = "blog.sgblog.MaxThread";
	public static final String STRING_YAOLAN_MICROBLOG_MAX_THREAD = "microblog.yaolan.MaxThread";
	
	public static final String STRING_HOTTOLINK_MAX_THREAD = "hottolink.MaxThread";
	
	
	public static final String STRING_USERNAME = "username";
	public static final String STRING_EMAIL = "email";
	public static final String STRING_PASSWORD = "password";

	public static final String NAVER_CHANNEL_URL = "http://cafe.naver.com/";
	public static final String DAUM_FORUM_CHANNEL_URL = "http://cafe.daum.net/";
	public static final String DAUM_BLOG_CHANNEL_URL = "http://blog.daum.net/";
	public static final String DAUM_QA_CHANNEL_URL = "http://k.daum.net/";
	public static final String WRETCH_BLOG_CHANNEL_URL = "http://tw.blog.search.yahoo.com/";
	public static final String TEACUP_BLOG_CHANNEL_URL = "http://find.teacup.com/";
	public static final String NAVER_BLOG_CHANNEL_URL = "http://blog.naver.com/";
	public static final String NAVER_QA_CHANNEL_URL = "http://kin.naver.com/";
	public static final String EGLOOS_BLOG_CHANNEL_URL = "http://www.egloos.com/";
	public static final String CYWORLD_BLOG_CHANNEL_URL = "http://home.cyworld.com/";
	public static final String CYWORLD_FORUM_CHANNEL_URL = "http://club.cyworld.com/";

	public static final String YAHOO_JP_CHANNEL_URL = "http://blog-search.yahoo.co.jp/";
	public static final String YAHOO_HK_CHANNEL_URL = "http://hk.myblog.yahoo.com/";
	public static final String YAHOO_VN_CHANNEL_URL = "http://vn.blog.search.yahoo.com/";
	public static final String DION_CHANNEL_URL = "http://blog.dion.ne.jp/";
	public static final String NIFTY_CHANNEL_URL = "http://search.nifty.com/";
	public static final String GOOGLE_CHANNEL_URL = "google";
	public static final String YOUDAO_CHANNEL_URL = "http://www.youdao.com/";
	public static final String SONET_CHANNEL_URL = "http://www.so-net.ne.jp/search/blog/";
	public static final String SEESAA_BLOG_CHANNEL_URL = "http://tag.seesaa.jp/t/blogs/";
	public static final String EXBLOG_CHANNEL_URL = "http://www.exblog.jp/";
	public static final String SEESAA_ARTICLE_CHANNEL_URL = "http://blog.seesaa.jp/";
	public static final String RAKUTEN_CHANNEL_URL = "http://plaza.rakuten.co.jp/";
	public static final String YAPLOG_CHANNEL_URL = "http://www.yaplog.jp/";
	public static final String GOO_CHANNEL_URL = "http://blog.goo.ne.jp/";
	public static final String NIFTY_MOBILE_CHANNEL_URL = "http://mobile.nifty.com/";
	public static final String AMEBA_CHANNEL_URL = "http://search.ameba.jp/";
	public static final String BAIDU_BLOG_CHANNEL_URL = "http://hi.baidu.com/";
	public static final String TISTORY_CHANNEL_URL = "http://www.tistory.com/";
	public static final String MIXI_CHANNEL_URL = "http://mixi.jp/search_diary.pl";
	public static final String LIVEDOOR_CHANNEL_URL = "http://livedoor.com/";
	public static final String PIXNET_CHANNEL_URL = "http://www.pixnet.net/";
	public static final String YOKA_CHANNEL_URL = "http://search.yoka.com/";
	public static final String YAHOO_TW_CHANNEL_URL = "http://tw.myblog.yahoo.com/";
	public static final String SINA_CHANNEL_URL = "http://uni.sina.com.cn/";
	public static final String KAIXIN001_CHANNEL_URL = "http://www.kaixin001.com/!repaste/";
	public static final String YOUTUBE_CHANNEL_URL = "http://www.youtube.com/";
	public static final String TUDOU_CHANNEL_URL = "http://www.tudou.com/";
	public static final String YOUKU_CHANNEL_URL = "http://www.youku.com/";
	public static final String POCO_CHANNEL_URL = "http://www.poco.cn/";
	public static final String FRIENDFEED_CHANNEL_URL = "http://friendfeed.com/";
	public static final String QQ_BLOG_CHANNEL_URL = "http://qzone.qq.com/index.html";
	public static final String SOHU_BLOG_CHANNEL_URL = "http://www.sogou.com/";
	public static final String BLOGBUS_CHANNEL_URL = "http://www.blogbus.com/";
	public static final String YAM_BLOG_CHANNEL_URL = "http://www.yam.com";
	
	public static final String T_SINA_CHANNEL_URL = "http://t.sina.com.cn/";
	public static final String T_MIXI_CHANNEL_URL = "http://mixi.jp/search_community.pl";
	public static final String T_SOHU_CHANNEL_URL = "http://t.sohu.com";
	public static final String T_BAIDU_CHANNEL_URL = "http://t.baidu.com/";
	public static final String T_IFENG_CHANNEL_URL = "http://t.ifeng.com";
	public static final String T_ME2DAY_CHANNEL_URL = "http://me2day.net/";
	public static final String T_PEOPLE_CHANNEL_URL = "http://t.people.com.cn";
	public static final String T_QQ_CHANNEL_URL = "http://t.qq.com/";
	public static final String T_163_CHANNEL_URL = "http://t.163.com/";
	public static final String T_TIANYA_CHANNEL_URL = "http://t.tianya.cn";

	public static final String TWITTER_ROOT_URL = "http://twitter.com/";

	public static final String FACEBOOK_LOGIN_URL = "https://login.facebook.com/login.php?login_attempt=1";
	public static final String FACEBOOK_SEARCH_URL = "https://graph.facebook.com/search?type=post&limit=500&q=";
	public static final String FACEBOOK_ROOT_URL = "https://graph.facebook.com/";

	// property file path
	public static final String CONFIG_PATH = "/brandtology/config/config.properties";
	public static final String DATABASE_CONFIG_PATH = "/brandtology/config/database.properties";
	public static final String GEOPROPERTIES_PATH = "/brandtology/config/geocode.properties";
	public static final String ACCOUNT_PATH = "/brandtology/config/account.properties";
	public static final String FACEBOOK_PATH = "/brandtology/config/facebook.properties";
	
	// channel
	public static final String SQL_QUERY_CHANNEL_BYID = "SELECT c.ID AS ID,Country,Search_Day_Range,Language,Category,Influence_Calculation_ID,Search_Day_Range,URL,Proxy_Host,Proxy_Host_Port,Search_Page_Range FROM Channel c,Proxy p WHERE c.ID = ? and c.Proxy_Host_ID=p.ID AND c.TO_MONITOR=1";
	public static final String SQL_QUERY_CHANNEL_BYCHECK = "SELECT ID FROM Channel WHERE URL = ?";
	public static final String SQL_INSERT_CHANNEL = "INSERT IGNORE INTO Channel(Name,Site_Type,URL,Language,Country,Category,Influence_Calculation_ID,Num_Inlinks,Crawler_Set_ID,To_Monitor,Parent_Channel_ID) VALUE (?,?,?,?,?,?,?,?,0,10,?) ON DUPLICATE KEY UPDATE Name=?;";
	public static final String SQL_INSERT_INFLUENCE_CHANNEL = "INSERT IGNORE INTO Channel(Name,Site_Type,URL,Language,Country,Category,Influence_Calculation_ID,Num_Inlinks,Influence_Score,Crawler_Set_ID,To_Monitor,Parent_Channel_ID) VALUE (?,?,?,?,?,?,?,?,?,0,10,?) ON DUPLICATE KEY UPDATE Name=?;";
	public static final String SQL_UPDATE_CHANNEL_FEEDER_LAST_TIME_UPDATE = "UPDATE Channel SET Feeder_Last_Time_Update = NOW() WHERE ID = ?";
	public static final String SQL_QUERY_CHANNEL_SITE_TYPE = "SELECT Site_Type FROM Channel WHERE ID = ?";
	
	// public static final String SQL_QUERY_CHANNEL_BYURL =
	// "SELECT c.ID AS ID,Country,Search_Day_Range,Language,Category,Influence_Calculation_ID,Search_Day_Range,Proxy_Host,Proxy_Host_Port,Search_Page_Range FROM Channel c, Proxy p WHERE URL = ? and c.Proxy_Host_ID=p.ID";
	public static final String SQL_QUERY_CHANNEL_BYURL = "SELECT c.ID AS ID,c.Country,c.Search_Day_Range,c.Language,c.Category,c.Influence_Calculation_ID,c.Search_Day_Range,p.Proxy_Host,p.Proxy_Host_Port,c.Search_Page_Range FROM Channel c, Proxy p WHERE URL = ? and c.Proxy_Host_ID=p.ID AND c.TO_MONITOR=1";
	
	// Article
	public static final String SQL_INSERT_ARTICLE = "INSERT IGNORE INTO Article(URL,Status,Title,Content,Channel_ID,Voice_ID,Voice_Name,DateTime_Feed,DateTime_Crawled,DateTime_Posted) VALUE (?,?,?,?,?,?,?,now(),now(),?);";
	public static final String SQL_INSERT_GOOGLE_ARTICLE = "INSERT IGNORE INTO Article(URL,Status,Title,Content,Channel_ID,Voice_ID,Voice_Name,DateTime_Feed,DateTime_Crawled,DateTime_Posted,Influence_Score) VALUE (?,?,?,?,?,?,?,now(),now(),?,?);";
	public static final String SQL_QUERY_ARTICLE = "SELECT ID FROM Article WHERE URL = ? AND Channel_ID = ?";
	public static final String SQL_QUERY_ARTICLE_BY_URL = "SELECT ID,Channel_ID FROM Article WHERE URL = ? AND Channel_ID=?";
	public static final String SQL_QUERY_ARTICLE_AUTHOR = "SELECT Voice_ID FROM Article WHERE ID = ?";
	
	public static final String SQL_INSERT_ARTICLE_SENTIMENT = "INSERT IGNORE INTO Article_Sentiment_Map(Article_Id,Sentiment_Score) VALUE (?,?)";
	
	// Author
	public static final String SQL_INSERT_AUTHOR = "INSERT IGNORE INTO Voice(Name,URL,Channel_ID,Author_Influence_Score,Site_Type,DateTime_Crawled,DateTime_Last_Updated) VALUE (?,?,?,?,?,now(),now()) ON DUPLICATE KEY UPDATE DateTime_Last_Updated = NOW() , Name = ?;";
	public static final String SQL_INSERT_GOOGLE_AUTHOR = "INSERT IGNORE INTO Voice(Name,URL,Channel_ID,Site_Type,DateTime_Crawled,DateTime_Last_Updated) VALUE (?,?,?,?,now(),now());";
	public static final String SQL_UPDATE_AUTHOR = "UPDATE Voice SET Author_Influence_Score = ? WHERE Name = ? AND CHANNEL_ID = ?";

	// Comment
	public static final String SQL_INSERT_COMMENT = "INSERT IGNORE INTO Comment(URL,Channel_ID,DateTime_Crawled,DateTime_Posted,Title,Content,Voice_ID,Voice_Name,Article_ID) VALUE(?,?,now(),?,?,?,?,?,?)";
	public static final String SQL_QUERY_COMMENT_EXIST = "SELECT ID FROM Comment WHERE DateTime_Posted = ? AND Content = ? AND  Voice_ID =? AND Article_ID=? LIMIT 1;";

	// Commenter
	public static final String SQL_INSERT_COMMENTER = "INSERT IGNORE INTO Voice(Name,URL,Channel_ID,Commenter_Influence_Score,Site_Type,DateTime_Crawled,DateTime_Last_Updated) VALUE (?,?,?,?,?,now(),now()) ON DUPLICATE KEY UPDATE DateTime_Last_Updated = NOW() , Name = ?;";
	public static final String SQL_UPDATE_COMMENTER = "UPDATE Voice SET Commenter_Influence_Score = ? WHERE Name = ? AND CHANNEL_ID = ?";

	// SearchTerm
	public static final String SQL_QUERY_SEARCH_TERM_BYCLIENT = "SELECT word FROM Search_Term WHERE Channel_ID = ? AND Client_Account_ID = ? ORDER BY Search_Last_Time_Update";
	public static final String SQL_QUERY_SEARCH_TERM_BYCHANNELANDCLIENT = "SELECT s.Channel_ID,s.Client_Account_ID,s.word,s.Search_Level from Search_Term s,Client_Account ca,Channel c,Subscribed_Channel sc where c.ID = sc.Channel_ID and s.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and ca.ID = s.Client_Account_ID and ca.Is_Active = 1 and c.ID = ? and ca.ID = ? and s.Search_Level in (?) ORDER BY Search_Last_Time_Update";
	public static final String SQL_QUERY_SEARCH_TERM_BYCHANNEL = "SELECT s.word from Search_Term s,Client_Account ca,Channel c,Subscribed_Channel sc where c.ID = sc.Channel_ID and s.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and ca.ID = s.Client_Account_ID and ca.Is_Active = 1 and c.ID = ? and s.Search_Level in (?) ORDER BY Search_Last_Time_Update limit 500";
	public static final String SQL_UPDATE_SEARCH_TERM = "UPDATE Search_Term SET Search_Last_Time_Update = now() WHERE Channel_ID = ? AND Word = ?";

	//Voice
	public static final String SQL_UPDATE_Voice_DateTime_Last_Updated = "UPDATE Voice SET DateTime_Last_Updated = NOW() WHERE ID = ?";
	public static final String SQL_QUERY_VOICE_BYCHECK = "SELECT ID FROM Voice WHERE URL= ? AND Channel_ID = ?";
	public static final String SQL_UPDATE_Voice_AuthorInfluenceScore = "UPDATE Voice SET Author_Influence_Score = ? WHERE Channel_ID = ? and URL = ?;";
	public static final String SQL_UPDATE_Voice_CommenterInfluenceScore = "UPDATE Voice SET Commenter_Influence_Score = ? WHERE ID = ?;";
	
	//Relationship_Mapping
	public static final String SQL_INSERT_RELATIONSHIP_MAPPING = "INSERT IGNORE INTO Relationship_Mapping(Channel_ID, Author_Voice_ID, Commenter_Voice_ID,DateTime_Crawled, DateTime_Posted) VALUE(?,?,?,now(),?);";
	public static final String SQL_QUERY_RELATIONSHIP_MAPPING = "SELECT ID FROM  Relationship_Mapping WHERE Channel_ID = ? AND Author_Voice_ID = ? AND Commenter_Voice_ID = ? AND DateTime_Posted = ?;";

	//Proxy
	public static final String SQL_QUERY_PROXY = "SELECT DISTINCT Proxy_Host,Proxy_Host_Port FROM Proxy where id <> 24 ORDER BY RAND();";
	
	// Client Account
	public static final String SQL_QUERY_CLIENT_ACCCOUNT = "SELECT ID,Name FROM Client_Account WHERE Is_Active = 1";

	// twitter
	public static final String SQL_QUERY_KEYWORDOFTWITTERBYCHANNELID = "select  st.channel_ID,st.Client_Account_ID,st.word,st.Search_Level  from Channel c, Subscribed_Channel sc, Client_Account ca, Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID  and st.Client_Account_ID = ca.ID and st.Channel_id = c.ID and st.Channel_id = ? and c.Site_Type = 'MICROBLOG' and c.To_Monitor = 1 and c.URL like 'http://twitter.com%' and st.Search_Level in (?) and ca.Is_Active = 1 order by st.Search_Last_Time_Update";
	// public static final String SQL_QUERY_CHANNELOFTWITTER =
	// "select c.id,c.Country,c.Search_Day_Range,p.Proxy_Host,c.Proxy_Host_Port from Channel c ,(select distinct(s.Channel_id) from Client_Account ca, Subscribed_Channel s where ca.id = s.Client_Account_ID AND ca.Is_Active= 1) sa ,Proxy p where c.Proxy_Host_ID=p.id and sa.Channel_id = c.id and c.URL LIKE 'http://twitter.com%' AND c.Site_Type = 'MICROBLOG' order by c.Feeder_Last_Time_Update";

	// google
	public static final String SQL_QUERY_GOOGLE_BLOG_CHANNELID = "SELECT ID FROM Channel WHERE URL like 'http://www.google%' AND Site_Type='BLOG_SEARCH'";
	public static final String SQL_QUERY_GOOGLE_CHANNEL = "SELECT c.ID,c.URL,c.Country,c.Search_Day_Range,c.Language,c.Category ,c.Influence_Calculation_ID,c.Proxy_Host_Port,p.Proxy_Host FROM Channel c,Proxy p WHERE p.ID = c.Proxy_Host_ID and c.Url like '%.google.%' AND c.Site_Type='BLOG_SEARCH' AND c.To_Monitor='1' ORDER BY c.Feeder_Last_Time_Update;";
	public static final String SQL_QUERY_BAIDU_CHANNEL = "SELECT c.ID,c.URL,c.Country,c.Search_Day_Range,c.Language,c.Category ,c.Influence_Calculation_ID,p.Proxy_Host_Port,p.Proxy_Host FROM Channel c,Proxy p WHERE p.ID = c.Proxy_Host_ID and c.Url like '%.baidu.%' AND c.Site_Type='BLOG_SEARCH' AND c.To_Monitor='1' ORDER BY c.Feeder_Last_Time_Update;";
	public static final String SQL_QUERY_KEYWORDOFGOOGLEBYCHANNELID ="select st.Channel_ID,st.Client_Account_ID,st.word from Channel c, Subscribed_Channel sc,Client_Account ca,Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID and st.Client_Account_ID = ca.ID and c.Site_Type = 'BLOG_SEARCH' and c.To_Monitor = 1 and c.URL like '%google%' and ca.Is_Active = 1 and c.ID=? and st.Search_Level in (?) order by st.Search_Last_Time_Update";

	//Search_Term
	public static final String SQL_QUERY_KEYWORD_BYURL ="select st.Channel_ID,st.Client_Account_ID,st.word,st.Search_Level from Channel c, Subscribed_Channel sc,Client_Account ca,Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID and st.Client_Account_ID = ca.ID and c.To_Monitor = 1 and c.URL like ? and ca.Is_Active = 1 and st.Search_Level in (?) group by st.Channel_ID, st.Word order by st.Search_Last_Time_Update Limit 500";
	public static final String SQL_QUERY_KEYWORD_BYURLLIMIT ="select st.Channel_ID,st.Client_Account_ID,st.word,st.Search_Level from Channel c, Subscribed_Channel sc,Client_Account ca,Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID and st.Client_Account_ID = ca.ID and c.To_Monitor = 1 and c.URL like ? and ca.Is_Active = 1 and st.Search_Level in (?) group by st.Channel_ID, st.Word order by st.ID Limit ?, ?";
	public static final String SQL_QUERY_KEYWORD_BYCLIENTIDURL ="select st.Channel_ID,st.Client_Account_ID,st.word,st.Search_Level from Channel c, Subscribed_Channel sc,Client_Account ca,Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID and st.Client_Account_ID = ca.ID and c.To_Monitor = 1 and c.URL like ? and ca.Is_Active = 1 and ca.ID=? and st.Search_Level in (?) order by st.Search_Last_Time_Update";
	public static final String SQL_QUERY_KEYWORD_BYWORD = "select st.Channel_ID,st.Client_Account_ID,st.word,st.Search_Level from Channel c, Subscribed_Channel sc,Client_Account ca,Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID and st.Client_Account_ID = ca.ID and c.To_Monitor = 1 and c.URL like ? and ca.Is_Active = 1 and st.id=? and st.Search_Level in (?) order by st.Search_Last_Time_Update";
	public static final String SQL_QUERY_KEYWORD_BYCHANNEL = "select st.Channel_ID,st.Client_Account_ID,st.word,st.Search_Level from Channel c, Subscribed_Channel sc,Client_Account ca,Search_Term st where sc.Channel_ID = c.ID and ca.ID = sc.Client_Account_ID and st.Channel_ID = c.ID and st.Client_Account_ID = ca.ID and c.To_Monitor = 1 and c.id = ? and ca.Is_Active = 1 and st.Search_Level in (?) order by st.Search_Last_Time_Update";
	// FaceBook
	public static final String SQL_QUERY_FACEBOOK_CHANNEL = "SELECT h.ID,h.URL,p.Proxy_Host,p.Proxy_Host_Port FROM (SELECT c.ID,c.URL,c.Proxy_Host_ID,c.Feeder_Last_Time_Update FROM Channel c,(SELECT DISTINCT(s.Channel_id) id FROM Client_Account ca, Subscribed_Channel s where ca.ID = s.Client_Account_ID AND ca.Is_Active= 1) as tmp where  tmp.id = c.ID  and c.URL LIKE 'https://graph.facebook.com%' AND c.To_Monitor = 1) h LEFT JOIN Proxy p on h.Proxy_Host_ID=p.ID ORDER BY h.Feeder_Last_Time_Update";
	public static final String SQL_UPDATE_FACEBOOK_AUTHOR_INFLUENCESCORE = "UPDATE Voice set Author_Influence_Score=? WHERE ID=?";
	public static final String SQL_UPDATE_FACEBOOK_COMMENTER_INFLUENCESCORE = "UPDATE Voice set Commenter_Influence_Score=? WHERE ID=?";

	//KEYWORD_RESULT
	public static final String SQL_INSERTORUPDATE_KEYWORDRESULT = "INSERT IGNORE INTO Keyword_Result(Date,Channel_ID,Keyword,Result_Count,Search_Level) VALUES (NOW(),?,?,1,?) ON DUPLICATE KEY UPDATE Result_Count=Result_Count+1;";
	public static final String SQL_INSERT_KEYWORDRESULT = "INSERT IGNORE INTO Keyword_Result(Date,Channel_ID,Keyword,Search_Level) VALUES (NOW(),?,?,?);";
	
	//查询系统时间
	public static final String SQL_TEST_CONNECTION = "SELECT SYSDATE()";
	
	public static final String STRING_CDATA_REGEX_START = "<!\\[CDATA\\[";
	public static final String STRING_CDATA_REGEX_END = "]]>";
	public static final String STRING_CDATA_REGEX_ALL = ".*";
	public static final String STRING_CDATA_REGEX_SPACE = "\\s*";
	public static final String STRING_TAG_VALUE_APPLICATION = "application/rss+xml";
	public static final String STRING_TAG_VALUE_GENERATOR = "generator";
	public static final String STRING_TAG_VALUE_L = "l";
	public static final String STRING_TAG_VALUE_NW = "nw";
	public static final String STRING_TAG_VALUE_ALTERNATE = "alternate";
	public static final String STRING_ARTICLE_URL_REGEX_1 = "<feedburner:origLink>.*?</feedburner:origLink>";
	public static final String STRING_ARTICLE_URL_REGEX_2 = "<link.*?</link>";
	public static final String STRING_ARTICLE_URL_REGEX_3 = "<link.*?/>";
	public static final String STRING_ARTICLE_TITLE_REGEX_1 = "<title.*?</title>";
	public static final String STRING_ARTICLE_TITLE_REGEX_2 = "<descriptiontitle>.*?</descriptiontitle>";
	public static final String STRING_ARTICLE_ITEM_REGEX_0 = "<item[\\s\\S]*?</item>";
	public static final String STRING_ARTICLE_ITEM_REGEX_1 = "<item.*?</item>";
	public static final String STRING_ARTICLE_ITEM_REGEX_2 = "<entry.*?</entry>";
	public static final String STRING_ARTICLE_CONTENT_REGEX_0 = "<description[\\s\\S]*?</description>";
	public static final String STRING_ARTICLE_CONTENT_REGEX_1 = "<content:encoded.*?</content:encoded>";
	public static final String STRING_ARTICLE_CONTENT_REGEX_2 = "<description.*?</description>";
	public static final String STRING_ARTICLE_CONTENT_REGEX_3 = "<content.*?</content>";
	public static final String STRING_ARTICLE_CONTENT_REGEX_4 = "<summary.*?</summary>";
	public static final String STRING_ARTICLE_CONTENT_REGEX_5 = "<atom:summary type='text'>.*?</atom:summary>";
	public static final String STRING_ARTICLE_DATEPOST_REGEX_1 = "(?i)<pubDate>.*?</pubDate>";
	public static final String STRING_ARTICLE_DATEPOST_REGEX_2 = "<dc:date>.*?</dc:date>";
	public static final String STRING_ARTICLE_DATEPOST_REGEX_3 = "<modified>.*?</modified>";
	public static final String STRING_ARTICLE_DATEPOST_REGEX_4 = "<published>.*?</published>";
	public static final String STRING_ARTICLE_DATEPOST_REGEX_5 = "<updated>.*?</updated>";
	public static final String STRING_ARTICLE_AUTHOR_REGEX_1 = "<name>.*?</name>";
	public static final String STRING_ARTICLE_AUTHOR_REGEX_2 = "<dc:creator>.*?</dc:creator>";
	public static final String STRING_ARTICLE_AUTHOR_REGEX_3 = "<author.*?</author>";
}
