package spyder;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BookMapper {
    @Select("select * from URL where url=#{url}")
    String select(@Param("url") String url);
    @Insert("insert into URL values(#{url})")
    void addURL(@Param("url")String url);
    void add(@Param("bookName") String bookName,@Param("author") String author);
    void addChina(@Param("code") String code,@Param("region") String region);
    @Insert("insert into xiaomi values(#{name},#{score})")
    void addXM(@Param("name") String name,@Param("score") int score);

}
