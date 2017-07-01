package com.oversea.api.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.oversea.common.encode.ThreeDES;

public class Test {
	public static Format LPADDING_FORMAT = new DecimalFormat("00");
	public static void main(String args[]) {
		
//		generaterInstanceByCustomer();
//		generaterInstanceByMonth();
//		generaterInstanceMap();
//		dropInstanceByMonth();
		
//		bizTrade();
//		pointDetail();
//		billUser();
//		follows();
//		follows_myfocus();
//		alarms();
//		feeds();
//		feed_comments();
//		feed_likes();
//		blogs();
//		user_feed();
		
//		biz_user_gift();
		
		
//		System.out.println(Md5Encrypt.md5("00000030004", "UTF-8"));
//		String data="ewoJInNpZ25hdHVyZSIgPSAiQWlpcTg2THV6MXFYWkYzMjc2cnRUQjlXK2FLMkpmelNJSE5Gelp4MDhkTFFZU1YvNHdsaTZYYm5ud2JxZ0xlRGx1RjZzbkczb2tjRCticG9Oa1hlR1o0YXJQNkI0MnZ3c0YydjZsbzZ6TUs0bnBCb1o3UUNxTFFsTXA2UWZMMjA3OFZiUDIzYUhmcHc1WGE4R1FjR2F6cWQxVzUrcktwT25pWFF4V0poMUx4ckFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBekxUQXpJREExT2pJeE9qVXlJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTlJQ0kxTW1NNU16RmxZVGhtTUdKallqVmpaVEprTkdRMU1tVTVNMkZrWWpabFpXTm1NekZqTkdWa0lqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREV3TXpJNU16a3lNeUk3Q2draVluWnljeUlnUFNBaU1TNHdJanNLQ1NKMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFd016STVNemt5TXlJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFek9UTTROVEk1TVRJd01EQWlPd29KSW5WdWFYRjFaUzEyWlc1a2IzSXRhV1JsYm5ScFptbGxjaUlnUFNBaU5EbERNRFl5TXpNdE1FTXdOUzAwTWpBeUxVRXpSa0l0TlRCQlFrVTBRall3UlRORklqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSnVkbk5vWlc1aWFURWlPd29KSW1sMFpXMHRhV1FpSUQwZ0lqZ3dNemN6TnpFek1DSTdDZ2tpWW1sa0lpQTlJQ0pqYjIwdWJuWnphR1Z1TG1sd2FHOXVaU0k3Q2draWNIVnlZMmhoYzJVdFpHRjBaUzF0Y3lJZ1BTQWlNVE01TXpnMU1qa3hNakF3TUNJN0Nna2ljSFZ5WTJoaGMyVXRaR0YwWlNJZ1BTQWlNakF4TkMwd015MHdNeUF4TXpveU1UbzFNaUJGZEdNdlIwMVVJanNLQ1NKd2RYSmphR0Z6WlMxa1lYUmxMWEJ6ZENJZ1BTQWlNakF4TkMwd015MHdNeUF3TlRveU1UbzFNaUJCYldWeWFXTmhMMHh2YzE5QmJtZGxiR1Z6SWpzS0NTSnZjbWxuYVc1aGJDMXdkWEpqYUdGelpTMWtZWFJsSWlBOUlDSXlNREUwTFRBekxUQXpJREV6T2pJeE9qVXlJRVYwWXk5SFRWUWlPd3A5IjsKCSJlbnZpcm9ubWVudCIgPSAiU2FuZGJveCI7CgkicG9kIiA9ICIxMDAiOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("receipt-data", data);
//		String url="https://sandbox.itunes.apple.com/verifyReceipt";
//		
//		String ret = HttpClientHelper.postJsonDataByUrl(url, true, "UTF-8", JSONUtil.Object2Json(params));
//		System.out.println(ret);
		
//		printfBuffer();
//		billUser();
		
//		ring_comments();
//		ring_likes();
//		blogs_external();
//		printfBuffer2();
//		delete();
//		modify();
//		audit_user_income();
//		int x=0;

//		jfPointDetail();
//		System.out.println(Integer.toBinaryString(x^8 + x^7 + x^6 + x^4 + x^2 + 1));
//		String[] arr= {};
//		StringBuffer blogExternal  = new StringBuffer();
//		for(String userId:arr) {
//			String postFix = StringUtil.getLastNumber(Long.parseLong(userId));
//			blogExternal.append("select a.seller, b.nick, count(1), ring_version from biz_trade_").append(postFix).append( " a, users b where a.seller=b.id and a.seller=").append(userId).append(" and status=40 and a.gmt_modified>='2014-08-04' group by ring_version union all").append("\n");
//		
////			for(int i = 0; i<100;  i++) {
////				blogExternal.append("insert into tmp_instances_map_0726 select id, ring_id, generator, customer, gmt_create , content from instances_").append(lpadding(i)).append( " where customer=").append(userId).append(" and version=3 ;").append("\n");
////			}
//		
//		}
		
//		System.out.println(blogExternal.toString());
//		try {
//			FileWriter fw = new FileWriter("/Users/ouburi/generator.sql");
//			fw.write(blogExternal.toString());
//			fw.flush();
//			
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(ThreeDES.decryptMode("200131"));;
	}
	
	
	public static void audit_user_income(){
		StringBuffer instanceSql  = new StringBuffer();
		for(int i=0;i<400; i++) {
			String postFix = formatDays(new Date(), i);
				instanceSql.append("CREATE TABLE audit_user_income_").append(postFix).append("(").append("\n")
				.append("id int(10) NOT NULL AUTO_INCREMENT,").append("\n")
				.append("user_id int(20) NOT NULL DEFAULT '0',").append("\n")
				.append("trade_income int(11) DEFAULT '0' COMMENT 'trade表收入',").append("\n")
				.append("bill_user_income int(11) DEFAULT '0' COMMENT 'bill_user表收入',").append("\n")
				.append("bill_ring_income int(11) DEFAULT '0' COMMENT 'bill_ring表收入',").append("\n")
				.append("income_status int(1) DEFAULT '0' COMMENT '状态：0 正常，1错误',").append("\n")
				.append("error_msg varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '错误信息',").append("\n")
				.append("buyer_count int(11) DEFAULT '0' COMMENT '购买人数',").append("\n")
				.append("nb int(11) DEFAULT '0' COMMENT '女神币',").append("\n")
				.append("ring_count int(11) DEFAULT '0' COMMENT '购买铃音数量',").append("\n")
				.append("nick varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,").append("\n")
				.append("PRIMARY KEY (id),").append("\n")
				.append("KEY user_id (user_id)").append("\n")
				.append(") ENGINE=MyISAM AUTO_INCREMENT=625 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='稽核用户收入';").append("\n");
		}
		System.out.println(instanceSql.toString());
	}
	
	public static void modify(){
		StringBuffer blogExternal  = new StringBuffer();
		
		
		
		for(int i = 0; i<100;  i++) {
			
			
			blogExternal.append("create index idx_get_up_record_gmt_create on get_up_record_").append(lpadding(i)).append(" (gmt_create);\n");
			
//			blogExternal.append("INSERT INTO get_up_record_all (id, user_id, alarm_id, alarm_time, cookie, gmt_create, create_date, status)")
//			.append("select id, user_id, alarm_id, alarm_time, cookie, gmt_create, create_date, status from core.get_up_record_").append(lpadding(i))
//			.append(" where gmt_create>=date_add(CURRENT_DATE(), INTERVAL -1 day) and gmt_create<CURRENT_DATE").append(";\n");
			
//			blogExternal.append("drop table feeds_").append(lpadding(i)).append(";\n");
			
//			blogExternal.append("create table feeds_").append(lpadding(i)).append(" as select * from core.feeds_").append(lpadding(i)).append(";\n");
			
//			blogExternal.append("select count(1) num from get_up_record_").append(lpadding(i)).append(" where create_date='2015-03-14' union all").append("\n");
			
//			blogExternal.append("select sum（point) from jf_point_detail_").append(lpadding(i)).append(" where direction=1 and date(gmt_create) = date_sub(current_date, interval 1 day) union all").append("\n");
//			blogExternal.append("alter table alarms_").append(lpadding(i)).append(" add awake_date datetime;").append("\n");
			
//			blogExternal.append("select concat('delete from instances_url_")
//			            .append(lpadding(i))
//			            .append(" where ip=3 and instance_id=',instance_id, ' limit ', num-1,';') from (")
//			            .append(" select count(1) num, instance_id from instances_url_").append(lpadding(i)).append(" group by instance_id, ip having count(1)>3) t").append("\n union all \n");;
			
//			blogExternal.append("update biz_trade_").append(lpadding(i)).append(" set status =40 where status =1 and ring_version=2;").append("\n");
//			blogExternal.append("ALTER TABLE instances_").append(lpadding(i)).append(" CHANGE content content VARCHAR(200)  NULL  DEFAULT NULL;").append("\n");
			
//	        blogExternal.append("create index idx_user_feed_").append(lpadding(i)).append("_usi on user_feed_").append(lpadding(i)).append(" (user_id, source_id, type);").append("\n");
//			blogExternal.append("delete from ring_locks_").append(lpadding(i))
//			.append(" where exists (select 1 from rings  where ring_locks_").append(lpadding(i)).append(".ring_id = rings.id and rings.version=2);").append("\n");
			//blogExternal.append("update instances_").append(lpadding(i)).append(" set is_forever='Y' ;").append("\n");

//			blogExternal.append("update instances_url_").append(lpadding(i)).append(" set url = replace(url,'media/download/','') ;").append("\n");

			
//					blogExternal.append("select content from instances_").append(lpadding(i)).append(" where version=3 union all").append("\n");
					
//			blogExternal.append("create index idx_feed_comments_").append(lpadding(i)).append("_created on feed_comments_").append(lpadding(i)).append("(gmt_create);\n");
//			blogExternal.append("create index idx_follows_").append(lpadding(i)).append("_uid_fansid on follows_").append(lpadding(i)).append("(user_id,funs_user_id);\n");
//			blogExternal.append("alter table follows_").append(lpadding(i)).append(" drop index user_id;").append("\n");
//			
//			blogExternal.append("create index idx_follows_myfocus__").append(lpadding(i)).append("_uid_fansid on follows_myfocus_").append(lpadding(i)).append("(user_id,myfocus_user_id);\n");
//			blogExternal.append("alter table follows_myfocus_").append(lpadding(i)).append(" drop index user_id;").append("\n");
			
//			blogExternal.append("alter table biz_trade_").append(lpadding(i)).append(" modify buyer bigint(20);").append("\n");
//			blogExternal.append("alter table biz_trade_").append(lpadding(i)).append(" modify seller bigint(20);").append("\n");
			
//			blogExternal.append("update alarms_").append(lpadding(i)).append(" set alarm_switch='YES' where alarm_siwtch is null;").append("\n");
			
//			blogExternal.append("select * from instances_")
//			.append(lpadding(i))
//			.append(" a where not exists (select 1 from biz_trade_")
//			.append(lpadding(i))
//			.append(" b where a.id = b.instance_id ) and ring_id != 20084")
//			.append("\n")
//			.append(" union all ")
//			.append("\n");
			
//			blogExternal.append("alter table instances_").append(lpadding(i)).append(" modify id bigint(20);").append("\n");
//			blogExternal.append("select count(1), instance_id, ip from instances_url_").append(lpadding(i)).append(" group by instance_id, ip having count(1)>1;").append("\n");
			
//			blogExternal.append("truncate table instances_url_").append(lpadding(i)).append(";").append("\n");
//			blogExternal.append("insert into instances_url_").append(lpadding(i)).append(" (instance_id, ip, url) select id ,19, concat('http://v.qichuang.com/media/download/',  video_id, '_T.mp4') from instances_").append(lpadding(i)).append(" where ring_type=0 and version not in (2) and video_id is not null;").append("\n");
//			blogExternal.append("insert into instances_url_").append(lpadding(i)).append(" (instance_id, ip, url) select id ,19, concat('http://v.qichuang.com/media/download/',  video_id, '_N.ns') from instances_").append(lpadding(i)).append(" where ring_type=0 and version in (2) and video_id is not null;").append("\n");
//			
//			blogExternal.append("insert into instances_url_").append(lpadding(i)).append(" (instance_id, ip, url) select id ,19, concat('http://v.qichuang.com/media/download/',  audio_id, '_T.mp3') from instances_").append(lpadding(i)).append(" where ring_type=1 and version not in (2) and audio_id is not null;").append("\n");
//			blogExternal.append("insert into instances_url_").append(lpadding(i)).append(" (instance_id, ip, url) select id ,19, concat('http://v.qichuang.com/media/download/',  audio_id, '_N.ns') from instances_").append(lpadding(i)).append(" where ring_type=1 and version in (2) and audio_id is not null;").append("\n");
			


			
			
			
			
//			blogExternal.append("alter table  instances_").append(lpadding(i)).append(" CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;").append("\n");
//			blogExternal.append("alter table  instances_").append(lpadding(i)).append(" MODIFY COLUMN content varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;").append("\n");
			
//			blogExternal.append("alter table  blogs_").append(lpadding(i)).append(" CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;").append("\n");
//			blogExternal.append("alter table  blogs_").append(lpadding(i)).append(" MODIFY COLUMN content varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;").append("\n");
//			blogExternal.append("alter table  feeds_").append(lpadding(i)).append(" MODIFY COLUMN content varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;").append("\n");
			
//			blogExternal.append("alter table  feeds_").append(lpadding(i)).append(" CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;").append("\n");
//			blogExternal.append("alter table  feeds_").append(lpadding(i)).append(" MODIFY COLUMN content varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;").append("\n");
//			
//			blogExternal.append("alter table  ring_comments_").append(lpadding(i)).append(" CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;").append("\n");
//			blogExternal.append("alter table  ring_comments_").append(lpadding(i)).append(" MODIFY COLUMN comments varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;").append("\n");
//			
//			blogExternal.append("alter table  feed_comments_").append(lpadding(i)).append(" CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;").append("\n");
//			blogExternal.append("alter table  feed_comments_").append(lpadding(i)).append(" MODIFY COLUMN comment varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL;").append("\n");
			
//			blogExternal.append("alter table  ring_comments_").append(lpadding(i)).append(" add (target_user_id bigint(20) DEFAULT NULL, target_comment_id bigint(20) DEFAULT NULL);").append("\n");
//			blogExternal.append("create index idx_ring_comments_target_uid on ring_comments_").append(lpadding(i)).append("  (target_user_id);").append("\n");
//			blogExternal.append("create index idx_ring_comments_target_commentid on ring_comments_").append(lpadding(i)).append("  (target_comment_id);").append("\n");			
			
			
			
			
			
			
//			blogExternal.append("alter TABLE instances_").append(lpadding(i)).append(" add is_forever varchar(1) default 'N';").append("\n");
//			blogExternal.append("alter TABLE instances_").append(lpadding(i)).append(" alter column is_forever set default 'N';").append("\n");
//			blogExternal.append("update instances_").append(lpadding(i)).append(" set is_forever='N' where is_forever is null;").append("\n");
//			blogExternal.append("alter table feeds_").append(lpadding(i)).append(" alter column picture_id_list varchar(1000) ;").append("\n");
//			blogExternal.append("alter table user_feed_").append(lpadding(i)).append(" alter status set default 0 ;").append("\n");
//			blogExternal.append("create index idx_alarm_creaated on  alarms_").append(lpadding(i)).append(" (gmt_create);").append("\n");
//			blogExternal.append("create index idx_biz_user_gift_creaated on  biz_user_gift_").append(lpadding(i)).append(" (gmt_create);").append("\n");
//			blogExternal.append("create index idx_feed_creaated on feeds_").append(lpadding(i)).append(" (gmt_create);").append("\n");
//			blogExternal.append("create index idx_follows_myfocus_creaated on  follows_myfocus_").append(lpadding(i)).append(" (gmt_create);").append("\n");
//			blogExternal.append("create index idx_instance_creaated on  instances_").append(lpadding(i)).append(" (gmt_create);").append("\n");
//			blogExternal.append("create index idx_user_feed_creaated on user_feed_").append(lpadding(i)).append(" (gmt_create);").append("\n");
		}
//		blogExternal.append("create index idx_biz_recharge_apple_receipt_creaated on biz_recharge_apple_receipt (gmt_create);").append("\n");
//		blogExternal.append("create index idx_biz_recharge_duomeng_receipt_creaated on biz_recharge_duomeng_receipt (gmt_create);").append("\n");
//		blogExternal.append("create index idx_biz_recharge_sp_receipt_creaated on biz_recharge_sp_receipt (gmt_create);").append("\n");
//		blogExternal.append("create index idx_invite_creaated on invite (gmt_create);").append("\n");
		System.out.println(blogExternal.toString());
	}
	
	public static void printfBuffer2(){
		StringBuffer printfBuffer  = new StringBuffer();
		
			
		for(int i = 0; i<100;  i++) {
			printfBuffer.append("alter table blogs_").append(lpadding(i)).append(" drop column count_likes;").append("\n");
			printfBuffer.append("alter table blogs_").append(lpadding(i)).append(" drop column count_comments;").append("\n");
			//printfBuffer.append("alter table blogs_").append(lpadding(i)).append(" modify column picture_id_list varchar(1000);").append("\n");
			//printfBuffer.append("alter table feeds_").append(lpadding(i)).append(" modify column picture_id_list varchar(1000);").append("\n");
		}
		System.out.println(printfBuffer.toString());
	}
	
	public static void printfBuffer(){
		StringBuffer printfBuffer  = new StringBuffer();
//		printfBuffer.append("delete from tag_ring where ring_id not in (select id from rings);").append(";\n");
//		printfBuffer.append("truncate table instances_map").append(";\n");
//		printfBuffer.append("delete from rings where generator in (20100,20101,20102,20200,20150);").append(";\n");
//		printfBuffer.append("delete from ring_external where user_id in (20100,20101,20102,20200,20150);").append(";\n");
//		printfBuffer.append("update users set level=0,point=0,follows=0,count_likes=0;").append(";\n");
//		printfBuffer.append("update generators set alarms=0;").append(";\n");
//		printfBuffer.append("update ring_external set alarms=0,total_orders=0, pending_orders=0,earning=0,count_comments=0,count_likes=0;").append(";\n");
//		printfBuffer.append("truncate table biz_recharge_apple_receipt;").append(";\n");
//		printfBuffer.append("truncate table biz_recharge_duomeng_receipt;").append(";\n");
//		printfBuffer.append("update user_balance set income=0,outcome=0,balance=0,adjust=0,ring_limit=100;").append(";\n");
			
		for(int i = 0; i<100;  i++) {
//			printfBuffer.append("alter table bill_ring_").append(lpadding(i)).append(" drop index uidx_bill_user ;").append("\n");
//			printfBuffer.append("create unique index uidx_bill_user on bill_user_").append(lpadding(i)).append("  (seller, bill_status, bill_date) ;").append("\n");

//			printfBuffer.append("truncate table alarms_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table user_feed_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table feeds_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table feed_likes_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table feed_comments_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table ring_comments_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table ring_likes_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table blogs_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table blog_external_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table bill_user_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table bill_ring_").append(lpadding(i)).append(";\n");
//			printfBuffer.append("truncate table instances_").append(lpadding(i)).append(";\n");
//			printfBuffer.append("truncate table point_detail_").append(lpadding(i)).append(";\n");
//			printfBuffer.append("truncate table biz_trade_").append(lpadding(i)).append(";\n");
//			printfBuffer.append("truncate table biz_user_gift_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table follows_").append(lpadding(i)).append(";\n");
			printfBuffer.append("truncate table follows_myfocus_").append(lpadding(i)).append(";\n");
			
		}
		System.out.println(printfBuffer.toString());
	}
	
	
	public static void blogs_external(){
		StringBuffer blogExternal  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			blogExternal.append("CREATE TABLE blog_external_").append(lpadding(i)).append("(").append("\n")
			  .append("blog_id int(11) NOT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("count_likes int(11) DEFAULT 0,").append("\n")
			  .append("count_comments int(11) DEFAULT 0,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (blog_id),").append("\n")
			  .append("KEY user_id (user_id)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		
		}
		System.out.println(blogExternal.toString());
	}
	
	public static void ring_likes(){
		StringBuffer ringCommentsBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			ringCommentsBuffer.append("CREATE TABLE ring_likes_").append(lpadding(i)).append("(").append("\n")
			  .append("id bigint(20) NOT NULL ,").append("\n")
			  .append("ring_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("key(ring_id),").append("\n")
			  .append("key(user_id)").append("\n")
			.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(ringCommentsBuffer.toString());
	}
	
	public static void ring_comments(){
		StringBuffer ringCommentsBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			ringCommentsBuffer.append("CREATE TABLE rings").append(lpadding(i)).append("(").append("\n")
			  .append("id bigint(20) NOT NULL ,").append("\n")
			  .append("ring_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("comments varchar(4000) DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("key(ring_id),").append("\n")
			  .append("key(user_id)").append("\n")
			.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(ringCommentsBuffer.toString());
	}
	
	public static void biz_user_gift(){
		StringBuffer userGiftBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			userGiftBuffer.append("CREATE TABLE biz_user_gift_").append(lpadding(i)).append("(").append("\n")
					.append("id int(11) NOT NULL,").append("\n")
					  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
					  .append("user_nick varchar(45) DEFAULT NULL,").append("\n")
					  .append("channel varchar(10) DEFAULT NULL,").append("\n")
					  .append("point int(11) DEFAULT NULL,").append("\n")
					  .append("status int(11) DEFAULT NULL,").append("\n")
					  .append("order_no varchar(45) DEFAULT NULL,").append("\n")
					  .append("time datetime DEFAULT NULL,").append("\n")
					  .append("out_order_no varchar(45) DEFAULT NULL,").append("\n")
					  .append("gmt_create datetime DEFAULT NULL,").append("\n")
					  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
					  .append("PRIMARY KEY (id),").append("\n")
					  .append(" KEY (user_id,channel),").append("\n")
					  .append(" KEY (status),").append("\n")
					  .append(" KEY (order_no),").append("\n")
					  .append(" KEY (out_order_no)").append("\n")
					  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(userGiftBuffer.toString());
	}
	
	
	public static void user_feed(){
		StringBuffer userFeedBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			userFeedBuffer.append("CREATE TABLE user_feed_").append(lpadding(i)).append("(").append("\n")
			  .append("id int(11) NOT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("feed_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("type int(3) DEFAULT NULL COMMENT 'feeds.type',").append("\n")
			  .append("status int(3) DEFAULT NULL,").append("\n")
			  .append("is_related int(11) DEFAULT NULL COMMENT '1：与我相关',").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY status (status),").append("\n")
			  .append("KEY feed_id_type (feed_id,type),").append("\n")
			  .append("KEY user_id (user_id)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(userFeedBuffer.toString());
	}
	
			  
			  
	
	public static void blogs(){
		StringBuffer blogsBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			blogsBuffer.append("CREATE TABLE blogs_").append(lpadding(i)).append("(").append("\n")
			  .append("id int(11) NOT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("content varchar(1024) DEFAULT '',").append("\n")
			  .append("status int(3) DEFAULT NULL,").append("\n")
			  .append("picture_id_list varchar(255) DEFAULT NULL,").append("\n")
			  .append("count_likes int(11) DEFAULT 0,").append("\n")
			  .append("count_comments int(11) DEFAULT 0,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY status (status),").append("\n")
			  .append("KEY user_id (user_id)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(blogsBuffer.toString());
	}
	
	
	public static void feed_likes(){
		StringBuffer feed_likesBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			feed_likesBuffer.append("CREATE TABLE feed_likes_").append(lpadding(i)).append("(").append("\n")
			  .append("id int(11) NOT NULL,").append("\n")
			  .append("blog_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY blog_id (blog_id),").append("\n")
			  .append("KEY user_id (user_id)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(feed_likesBuffer.toString());
	}
	
	
	public static void feed_comments(){
		StringBuffer feedsCommentsBuffer  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			feedsCommentsBuffer.append("CREATE TABLE feed_comments_").append(lpadding(i)).append("(").append("\n")
			  .append("id int(11) NOT NULL,").append("\n")
			  .append("blog_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("target_user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("target_comment_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("comment varchar(1024) DEFAULT NULL,").append("\n")
			  .append("status int(3) DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY blog_id (blog_id),").append("\n")
			  .append("KEY user_id (user_id),").append("\n")
			  .append("KEY target_user_id (target_user_id),").append("\n")
			  .append("KEY status (status)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(feedsCommentsBuffer.toString());
	}
	
	public static void feeds(){
		StringBuffer feeds  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			feeds.append("CREATE TABLE feeds_").append(lpadding(i)).append("(").append("\n")
			  .append("id bigint(20) NOT NULL,").append("\n")
			  .append("from_user_id bigint(20) NOT NULL,").append("\n")
			  .append("to_user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("type int(11) NOT NULL COMMENT '0:blog; 1:like; 2:comment; 3:Comment2Comment; 4:ring; 5:private ring; 6:focus; 99:system; ',").append("\n")
			  .append("source_id bigint(20) NOT NULL,").append("\n")
			  .append("content varchar(1024) DEFAULT '' COMMENT 'type=0:blog;  type=2:comments; type =99:system',").append("\n")
			  .append("status int(11) NOT NULL,").append("\n")
			  .append("picture_id_list varchar(255) DEFAULT '',").append("\n")
			  .append("gmt_create datetime NOT NULL,").append("\n")
			  .append("gmt_modified datetime NOT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY from_user_id (from_user_id),").append("\n")
			  .append("KEY to_user_id (to_user_id),").append("\n")
			  .append("KEY source_id_type (source_id,type),").append("\n")
			  .append("KEY status (status)").append("\n")
			.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(feeds.toString());
	}
	
	
	public static void alarms(){
		StringBuffer alarmsSQL  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			alarmsSQL.append("CREATE TABLE alarms_").append(lpadding(i)).append("(").append("\n")
			  .append("id bigint(20) NOT NULL,").append("\n")
			  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("alarm_time varchar(45) DEFAULT NULL,").append("\n")
			  .append("alarm_repeat varchar(45) DEFAULT NULL,").append("\n")
			  .append("alarm_delay varchar(45) DEFAULT NULL,").append("\n")
			  .append("alarm_generator bigint(20) DEFAULT NULL,").append("\n")
			  .append("alarm_ring_type int(11) DEFAULT NULL,").append("\n")
			  .append("alarm_ring bigint(20) DEFAULT NULL,").append("\n")
			  .append(" alarm_ring_instance varchar(45) DEFAULT NULL,").append("\n")
			  .append("status int(11) DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append(" gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY user_id (user_id),").append("\n")
			  .append("KEY alarm_ring (alarm_ring),").append("\n")
			  .append("KEY alarm_ring_instance (alarm_ring_instance),").append("\n")
			  .append("KEY status (status)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(alarmsSQL.toString());
	}
	

	
	public static void follows_myfocus(){
		StringBuffer follows_myfocus  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			follows_myfocus.append("CREATE TABLE follows_myfocus_").append(lpadding(i)).append("(").append("\n")
				  .append("id int(11) NOT NULL,").append("\n")
				  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
				  .append("myfocus_user_id bigint(20) DEFAULT NULL,").append("\n")
				  .append("status int(11) DEFAULT NULL,").append("\n")
				  .append("gmt_create datetime DEFAULT NULL,").append("\n")
				  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
				  .append("PRIMARY KEY (id),").append("\n")
				  .append("KEY user_id (user_id)").append("\n")
				.append(") ENGINE=InnoDB  DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(follows_myfocus.toString());
	}
	
	public static void follows(){
		StringBuffer followsSQL  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			followsSQL.append("CREATE TABLE follows_").append(lpadding(i)).append("(").append("\n")
				  .append("id int(11) NOT NULL,").append("\n")
				  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
				  .append("funs_user_id bigint(20) DEFAULT NULL,").append("\n")
				  .append("status int(11) DEFAULT NULL,").append("\n")
				  .append("gmt_create datetime DEFAULT NULL,").append("\n")
				  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
				  .append("PRIMARY KEY (id),").append("\n")
				  .append("KEY user_id (user_id)").append("\n")
				.append(") ENGINE=InnoDB  DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(followsSQL.toString());
	}
	
	public static void generaterInstanceMap() {
		StringBuffer instanceMapSql  = new StringBuffer();
		instanceMapSql.append("CREATE TABLE instances_map").append("(").append("\n")
		  .append("instance_id int(11) DEFAULT NULL,").append("\n")
		  .append("ring_id int(11) DEFAULT NULL,").append("\n")
		  .append("generator varchar(45) DEFAULT NULL,").append("\n")
		  .append("customer varchar(45) DEFAULT NULL,").append("\n")
		  .append("gmt_create date DEFAULT NULL,").append("\n")
		  .append("PRIMARY KEY (instance_id),").append("\n")
		   .append("KEY idx_instance_map_ring_id").append("(ring_id),").append("\n")
		   .append("KEY idx_instance_map_generator").append("(generator),").append("\n")
		   .append("KEY idx_instance_map_customer").append(" (customer)").append("\n")
		.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n").append("\n");
		System.out.println(instanceMapSql.toString());
	}
	
	
	
	public static void billRing() {
		StringBuffer billUserSQL  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			billUserSQL.append("CREATE TABLE bill_ring_").append(lpadding(i)).append("(").append("\n")
			 .append("id bigint(20) NOT NULL,").append("\n")
			  .append("bill_date varchar(50) NOT NULL COMMENT '统计日期',").append("\n")
			  .append("seller bigint(20) NOT NULL,").append("\n")
			  .append("ring_id bigint(20) DEFAULT NULL,").append("\n")
			  .append("buyer_count bigint(20) DEFAULT NULL COMMENT '订制人数',").append("\n")
			  .append("nb bigint(20) DEFAULT NULL COMMENT '神币',").append("\n")
			  .append("rmb bigint(20) DEFAULT NULL COMMENT '人民币',").append("\n")
			  .append("bill_status int(11) DEFAULT NULL COMMENT '订单状态（已结算、未结算）',").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY idx_bill_ring_seller").append(lpadding(i)).append("(seller),").append("\n")
			  .append("KEY idx_bill_ring_status").append(lpadding(i)).append("(bill_status)").append("\n")
			.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的月账单明细';").append("\n");
		}
		System.out.println(billUserSQL.toString());
	}
	public static void billUser() {
		StringBuffer billUserSQL  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			billUserSQL.append("CREATE TABLE bill_user_").append(lpadding(i)).append("(").append("\n")
			  .append("id bigint(20) NOT NULL,").append("\n")
			  .append("bill_date varchar(50) NOT NULL COMMENT '统计日期',").append("\n")
			  .append("seller bigint(20) NOT NULL,").append("\n")
			  .append("ring_count bigint(20) DEFAULT NULL COMMENT '铃声数量',").append("\n")
			  .append("buyer_count bigint(20) DEFAULT NULL COMMENT '订制人数',").append("\n")
			  .append("nb bigint(20) DEFAULT NULL COMMENT '神币',").append("\n")
			  .append("rmb bigint(20) DEFAULT NULL COMMENT '人民币',").append("\n")
			  .append("bill_status int(11) DEFAULT NULL COMMENT '账单状态（已结算、未结算）',").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			  .append("KEY idx_bill_user_seller").append(lpadding(i)).append("(seller),").append("\n")
			  .append("KEY idx_bill_user_status").append(lpadding(i)).append("(bill_status)").append("\n")
			  .append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收入账单';").append("\n");
		}
		System.out.println(billUserSQL.toString());
	}
	
	public static void pointDetail() {
		StringBuffer pointDetailSQL  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			pointDetailSQL.append("CREATE TABLE point_detail_").append(lpadding(i)).append("(").append("\n")
		.append("id int(11) NOT NULL,").append("\n")
				  .append("user_id int(11) DEFAULT NULL,").append("\n")
				  .append("user_nick varchar(45) DEFAULT NULL,").append("\n")
				  .append("point int(11) DEFAULT NULL,").append("\n")
				  .append("direction int(11) DEFAULT NULL,").append("\n")
				  .append("biz_type int(11) DEFAULT NULL,").append("\n")
				  .append("biz_id int(11) DEFAULT NULL,").append("\n")
				  .append("status int(11) DEFAULT NULL,").append("\n")
				  .append("gmt_create datetime DEFAULT NULL,").append("\n")
				  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
				  .append("PRIMARY KEY (id),").append("\n")
				  .append("KEY uidx_point_detail_uid_id_type").append(lpadding(i)).append("(user_id, biz_id,biz_type)").append("\n")
				.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(pointDetailSQL.toString());
	}	
	public static void bizTrade() {
		StringBuffer tradeSql  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			tradeSql.append("CREATE TABLE biz_trade_").append(lpadding(i)).append("(").append("\n")
				.append("id int(11) NOT NULL,").append("\n")
				.append("buyer int(11) DEFAULT NULL,").append("\n")
				.append("seller int(11) DEFAULT NULL,").append("\n")
				.append("trade_time datetime DEFAULT NULL,").append("\n")
				.append("ring_id bigint(20) DEFAULT NULL,").append("\n")
				.append("ring_version int(11) DEFAULT NULL,").append("\n")
				.append("instance_id int(11) DEFAULT NULL,").append("\n")
				.append("days int(11) DEFAULT NULL,").append("\n")
				.append("price int(11) DEFAULT NULL,").append("\n")
				.append("point int(11) DEFAULT NULL,").append("\n")
				.append("amout_belong_seller int(11) DEFAULT NULL,").append("\n")
				.append("amount_belong_platform int(11) DEFAULT NULL,").append("\n")
				.append("status int(11) DEFAULT NULL,").append("\n")
				.append("cashing_status int(11) DEFAULT NULL COMMENT '和卖家的结算状态',").append("\n")
				.append("cashing_time datetime DEFAULT NULL,").append("\n")
				.append("gmt_create datetime DEFAULT NULL,").append("\n")
				.append("gmt_modified datetime DEFAULT NULL,").append("\n")
				.append("PRIMARY KEY (id),").append("\n")
				.append("KEY idx_trade_buyer").append(lpadding(i)).append("(buyer),").append("\n")
				.append("KEY idx_trade_seller_ring_id").append(lpadding(i)).append("(seller, ring_id),").append("\n")
				.append("KEY idx_trade_ring_id").append(lpadding(i)).append("(ring_id),").append("\n")
				.append("KEY idx_trade_instance_id").append(lpadding(i)).append("(instance_id),").append("\n")
				.append("KEY idx_trade_gmt_create").append(lpadding(i)).append("(gmt_create),").append("\n")
				.append("KEY idx_trade_status").append(lpadding(i)).append("(status),").append("\n")
				.append("KEY idx_trade_cashing_status").append(lpadding(i)).append("(cashing_status)").append("\n")
				.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(tradeSql.toString());
	}
	
	public static void generaterInstanceByCustomer() {

		StringBuffer instanceSql  = new StringBuffer();
//		StringBuffer instanceMapSql  = new StringBuffer();
//		StringBuffer instanceDropSql  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
//			instanceDropSql.append("drop TABLE instances_").append(lpadding(i)).append(";\n");
			instanceSql.append("CREATE TABLE instances_").append(lpadding(i)).append("(").append("\n")
			.append("id varchar(45) NOT NULL,").append("\n")
			  .append("ring_id int(11) DEFAULT NULL,").append("\n")
			  .append("generator varchar(45) DEFAULT NULL,").append("\n")
			  .append("customer varchar(45) DEFAULT NULL,").append("\n")
			  .append("version int(11) DEFAULT NULL,").append("\n")
			  .append("cover varchar(256) DEFAULT NULL,").append("\n")
			  .append("ring_type varchar(45) DEFAULT NULL,").append("\n")
			  .append("video_id varchar(45) DEFAULT NULL,").append("\n")
			  .append("video_length int(11) DEFAULT NULL,").append("\n")
			  .append("audio_id varchar(45) DEFAULT NULL,").append("\n")
			  .append("audio_length int(11) DEFAULT NULL,").append("\n")
			  .append("picture_id_list varchar(512) DEFAULT NULL,").append("\n")
			  .append("status varchar(45) DEFAULT NULL,").append("\n")
			  .append("point int(10) DEFAULT NULL,").append("\n")
			  .append("days int(10) DEFAULT NULL,").append("\n")
			  .append("content varchar(100) DEFAULT NULL,").append("\n")
			  .append("expired_time datetime DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			   .append("KEY idx_instance_ring_id").append(lpadding(i)).append("(ring_id),").append("\n")
			   .append("KEY idx_instance_generator").append(lpadding(i)).append("(generator),").append("\n")
			   .append("KEY idx_instance_customer").append(lpadding(i)).append(" (customer)").append("\n")
			.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n").append("\n");
		}
		
		System.out.println(instanceSql.toString());
	}
	
	public static void generaterInstanceByMonth() {
		StringBuffer instanceSql  = new StringBuffer();
		for(int i=0;i<24; i++) {
			String postFix = formatMonth(new Date(), i);
			instanceSql.append("CREATE TABLE instances_").append(postFix).append("(").append("\n")
			.append("id varchar(45) NOT NULL,").append("\n")
			  .append("ring_id int(11) DEFAULT NULL,").append("\n")
			  .append("generator varchar(45) DEFAULT NULL,").append("\n")
			  .append("customer varchar(45) DEFAULT NULL,").append("\n")
			  .append("version int(11) DEFAULT NULL,").append("\n")
			  .append("cover varchar(256) DEFAULT NULL,").append("\n")
			  .append("ring_type varchar(45) DEFAULT NULL,").append("\n")
			  .append("video_id varchar(45) DEFAULT NULL,").append("\n")
			  .append("video_length int(11) DEFAULT NULL,").append("\n")
			  .append("audio_id varchar(45) DEFAULT NULL,").append("\n")
			  .append("audio_length int(11) DEFAULT NULL,").append("\n")
			  .append("picture_id_list varchar(512) DEFAULT NULL,").append("\n")
			  .append("status varchar(45) DEFAULT NULL,").append("\n")
			  .append("point int(10) DEFAULT NULL,").append("\n")
			  .append("days int(10) DEFAULT NULL,").append("\n")
			  .append("content varchar(100) DEFAULT NULL,").append("\n")
			  .append("expired_time datetime DEFAULT NULL,").append("\n")
			  .append("gmt_create datetime DEFAULT NULL,").append("\n")
			  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
			  .append("PRIMARY KEY (id),").append("\n")
			   .append("KEY idx_instance_ring_id").append(postFix).append("(ring_id),").append("\n")
			   .append("KEY idx_instance_generator").append(postFix).append("(generator),").append("\n")
			   .append("KEY idx_instance_customer").append(postFix).append(" (customer)").append("\n")
			.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n").append("\n");
		}
		System.out.println(instanceSql.toString());
	}
	
	public static String lpadding(int src) {
		return LPADDING_FORMAT.format(src);
	}
	
	public static String formatMonth(Date date, int interval) {
		DateFormat ymdFormat = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, interval);
		return ymdFormat.format(cal.getTime());
	}
	
	public static String formatDays(Date date, int interval) {
		DateFormat ymdFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, interval);
		return ymdFormat.format(cal.getTime());
	}
	
	public static void jfPointDetail() {
		StringBuffer pointDetailSQL  = new StringBuffer();
		for(int i = 0; i<100;  i++) {
			pointDetailSQL.append("CREATE TABLE jf_point_detail_").append(lpadding(i)).append("(").append("\n")
		          .append("id bigint(20) NOT NULL,").append("\n")
				  .append("user_id bigint(20) DEFAULT NULL,").append("\n")
				  .append("direction int(2)  DEFAULT NULL,").append("\n")
				  .append("point int(11) DEFAULT NULL,").append("\n")
				  .append("biz_type int(5) DEFAULT NULL,").append("\n")
				  .append("biz_id bigint(20) DEFAULT NULL,").append("\n")
				  .append("status int(5) DEFAULT NULL,").append("\n")
				  .append("gmt_create datetime DEFAULT NULL,").append("\n")
				  .append("gmt_modified datetime DEFAULT NULL,").append("\n")
				  .append("PRIMARY KEY (id),").append("\n")
				  .append("KEY idx_jf_point_detail_uid_id_type(user_id, biz_id,biz_type),").append("\n")
				  .append("KEY gmt_create(gmt_create)").append("\n")
				.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append("\n");
		}
		System.out.println(pointDetailSQL.toString());
	}
}


