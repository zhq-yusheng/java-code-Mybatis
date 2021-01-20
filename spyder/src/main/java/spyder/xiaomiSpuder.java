package spyder;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import utli.MybatisUtli;

import java.io.IOException;
import java.net.URL;
import java.util.List;


class XiaomiSpyder {
    public static void main(String[] args) throws IOException {

        Document parse = Jsoup.parse(new URL("https://app.mi.com/category/15#page=0"), 10000);
        JXDocument jxDocument = new JXDocument(parse.getAllElements());
        List<JXNode> jxNodes = jxDocument.selN("//*[@class=\"hd\"]/@href");
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        for (JXNode jxNode : jxNodes) {
            String url = "https://app.mi.com"+jxNode;
            String html = Jsoup.parse(new URL(url), 1000).toString();
            String score= html.split("class=\"star1-hover star1-")[1].split("\">")[0];
            String name = html.split("margin-top: 18px\">")[1].split("</h3>")[0];
            mapper.addXM(name,Integer.parseInt(score));//添加数据库
            sqlSession.commit();
        }
        System.out.println("添加完成");
}
}
