package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
//			データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"86js37Xz&mys"
					);
			
			System.out.println("データベース接続成功");
			
//			sql文の準備
			String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES"
					+ "(1003, '2023-02-08', '昨日の夜は徹夜でした・・・', 13),"
					+ "(1002, '2023-02-08', 'お疲れ様です!', 12),"
					+ "(1003, '2023-02-09', '今日も頑張ります!', 18),"
					+ "(1001, '2023-02-09', '無理は禁物ですよ!', 17),"
					+ "(1002, '2023-02-10', '明日から連休ですね!', 20);";
			
			statement = con.createStatement();
			
//			sql文を実行してレコードを追加
			int rowCnt = 0;
			
			System.out.println("レコード追加を実行します" );
			rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが追加されました");
			
			
//			データの検索
			int id = 1002;
			String sql_2 = "SELECT posted_at, post_content, likes FROM posts WHERE user_id =" + id + ";";
			
//			検索結果を表示
			ResultSet result = statement.executeQuery(sql_2);
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			
			while(result.next()) {
				Date date = result.getDate("posted_at");
				String  content = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目 : 投稿日時=" + date + "/投稿内容=" + content + "/いいね数=" + likes);				
			}
			
		} catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 使用したオブジェクトを解放
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
        }
		

	}

}
