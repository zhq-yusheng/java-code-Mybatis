package spyder;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import utli.MybatisUtli;
import java.net.URL;
import java.util.List;


public class ChinaSpyder {
    public static void main(String[] args) throws Exception {
        Document document = Jsoup.parse(new URL("http://www.mca.gov.cn/article/sj/xzqh/2020/"), 1000);
        List<JXNode> jxNodes = new JXDocument(document.getAllElements()).selN("//*[@id=\"list_content\"]/div[2]/div/ul/table/tbody/tr[*]/td[2]/a");
        String falseURL=null;
        for (int i = 0; i < jxNodes.size(); i++) {
            if(jxNodes.get(i).selOne("./@title").toString().endsWith("行政区划代码")){
                falseURL="http://www.mca.gov.cn"+jxNodes.get(i).sel("./@href").get(0).toString();
                break; } }
        Document dom = Jsoup.parse(new URL(falseURL), 1000);
        String trueURL=dom.toString().split("window\\.location\\.href=\"")[1].split("\";")[0];
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        if(mapper.select(trueURL)!=null) {
            System.out.println("已经是最新数据了");
            }else{
            mapper.addURL(trueURL);
            sqlSession.commit();
            Document parse = Jsoup.parse(new URL(trueURL), 1000000);
            JXDocument jxDocument = new JXDocument(parse.getAllElements());
            List<JXNode> jxNodes1 = jxDocument.selN("//tr[@height=\"19\"]");
            for (JXNode jxNode : jxNodes1) {
                String code = null;
                String region = null;
                try {
                    region = jxNode.selOne("./td[3]/text()").toString();
                    code = jxNode.selOne("./td[2]/text()").toString();
                } catch (Exception e) {
                    code = jxNode.selOne("./td[2]/span/text()").toString();
                }
                mapper.addChina(code, region);
                sqlSession.commit();
            }
        }
        System.out.println("爬取完成！！！！！");
    }
}
