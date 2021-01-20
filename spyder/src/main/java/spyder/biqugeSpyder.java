package spyder;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import utli.MybatisUtli;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class biqugeSpyder {
    public static void main(String[] args) throws IOException {
        Map<String,String> headers=new HashMap<String, String>();
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");
        Map<String,String> cs = new HashMap<String, String>();
        cs.put("action","login");
        cs.put("username","159258456");
        cs.put("password","325188");
        cs.put("usecookie","720");
        Connection connect = Jsoup.connect("https://www.biquge.lol/MemberAction.php");
        Connection.Response execute = connect.headers(headers).data(cs).ignoreContentType(true).execute();
        Connection connect1 = Jsoup.connect("https://www.biquge.lol/user/case.php");//登陆获取cookie
        Document document = connect1.headers(headers).cookies(execute.cookies()).get();//拿到cookie请求书架
        System.out.println(document);
        JXDocument jxDocument = new JXDocument(document.getAllElements());
        List<JXNode> BookNames = jxDocument.selN("//tbody/tr[*]/td[2]/a/text()");//书名列表
        List<JXNode> authors = jxDocument.selN("//tbody/tr[*]/td[3]/text()");//作者列表
        SqlSession session = MybatisUtli.getSqlSession(); //存入数据库
        BookMapper mapper = session.getMapper(BookMapper.class);
        for (int i = 0; i < 3; i++) {
            mapper.add(BookNames.get(i).toString(),authors.get(i).toString());
            session.commit();//提交事务
        }
        System.out.println("爬取 存入数据库成功！");
    }
}
