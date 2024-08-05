package com.itgroup.dao;

import com.itgroup.bean.KimKwangSuk;
import com.itgroup.utility.Paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
String sql = " select tracknumber,title,company,image,image02,image03,stock,price,category,contents,point,inputdate ";
sql += "from (";
sql +=  " select tracknumber,title,company,image,image02,image03,stock,price,category,contents,point,inputdate, ";
sql += "rank() over (order by tracknumber desc) as ranking" ;
sql +=" from KimKwangSuks";
sql +=" );
sql += " where ranking between? and? ";
 */

public class KimKwangSukDao extends SuperDao {

    //생성자 만들기
    public KimKwangSukDao() {
        //2)
        super();
    }

    public List<KimKwangSuk> selectAll() {//모든 곡 조회하기
        Connection conn = null;
        //오라클의 세미콜롬(;)은 적지 않는다
        String sql = "select * from KimKwangSuk order by tracknumber desc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<KimKwangSuk> allData = new ArrayList<KimKwangSuk>();

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            //내려가려면 next사용
            while (rs.next()) {

                KimKwangSuk bean = this.makeBean(rs);
                allData.add(bean);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return allData;
    }

    private KimKwangSuk makeBean(ResultSet rs) {
        KimKwangSuk bean = new KimKwangSuk();
        try {
            bean.setTracknumber(rs.getInt("tracknumber"));
            bean.setTitle(rs.getString("title"));
            bean.setImage(rs.getString("image"));
            bean.setGenre(rs.getString("genre"));
            bean.setLyrics(rs.getString("lyrics"));
            bean.setExplanation(rs.getString("explanation"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bean;
    }

    public List<KimKwangSuk> selectByCategory(String category) {
        Connection conn = null;

        String sql = " select * from KimKwangSuk ";

        boolean bool = category == null || category.equals("all");
        if (!bool) {
            sql += " where category = ? ";
        }

        //문장 객체
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<KimKwangSuk> allData = new ArrayList<>();
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            //값을 바꾸려면 set,치환하고 실행하기 때문에
            //set는 executeQuery 앞에

            if (!bool) {
                pstmt.setString(1, category);
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                KimKwangSuk bean = this.makeBean(rs);
                allData.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return allData;
    }

    public KimKwangSuk selectByPK(int tracknumber) {
        Connection conn = null;
        String sql = " select * from KimKwangSuk ";
        sql += " where tracknumber = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        KimKwangSuk bean = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tracknumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = this.makeBean(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return bean;
    }

    public int insertData(KimKwangSuk bean) {
        // Resultset은 출력할 때 사용
        //데이터가 잘 들어왔는지 확인
        System.out.println(bean);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " insert into KimKwangSuk(tracknumber, title, image, genre, lyrics, explanation)";
        sql += " values(songIdx.nextval,?,?,?,?,?)";


        try {
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으로 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);


            //치환할때는 excuteUpdate전에 구문 사용
            //cnt 삭제,추가로 업데이트 사용
            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getImage());
            pstmt.setString(3, bean.getGenre());
            pstmt.setString(4, bean.getLyrics());
            pstmt.setString(5, bean.getExplanation());

            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int updateData(KimKwangSuk bean) {
        String oldData = bean.getOldData();

        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " update KimKwangSuk set title = ?,image = ?,genre = ?,lyrics = ?,explanation = ? ";
        sql += " where tracknumber = ? ";

        try {
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으로 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);


            //치환할때는 excuteUpdate전에 구문 사용
            //cnt 삭제,추가로 업데이트 사용
            System.out.println("================");
            System.out.println(bean.getTracknumber());
            System.out.println("================");
            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getImage());
            pstmt.setString(3, bean.getGenre());
            pstmt.setString(4, bean.getLyrics());
            pstmt.setString(5, bean.getExplanation());
            pstmt.setInt(6, bean.getTracknumber());


            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int deleteData(int tracknumber) {

        System.out.println("기본 키 = " + tracknumber);
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = " delete from KimKwangSuk ";
        sql += " where tracknumber = ? ";


        try {
            //객체 생성
            conn = super.getConnection();
            //자동커밋을 끄고 한번에 커밋으로 하겠다 선언,
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, tracknumber);

            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int getTotalCount(String genre) {

        int getTotalCount = 0;
        String sql = " select count (*) as mycnt from KimKwangSuk ";
        boolean bool = genre == null || genre.equals("all");
        //카테고리를 넣지 않으면 실행되지 않음
        if (!bool) {
            sql += " where genre = ? ";
        }

        Connection conn = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (!bool) {
                pstmt.setString(1, genre);
            }

            rs = pstmt.executeQuery();

            if (rs.next()) {
                getTotalCount = rs.getInt("mycnt");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return getTotalCount;
    }

    public List<KimKwangSuk> getPaginationData(Paging pageInfo) {
        Connection conn = null;

        String sql = " select tracknumber, title, image, genre, lyrics, explanation ";
        sql += " from ( ";
        sql += " select tracknumber, title, image, genre, lyrics, explanation, ";
        sql += " rank() over(order by tracknumber desc) as ranking ";
        sql += " from KimKwangSuk ";


        // mode가 'all'이 아니면 where 절이 추가로 필요합니다.
        String mode = pageInfo.getMode();
        boolean bool = mode.equals(null) || mode.equals("null") || mode.equals("") || mode.equals("all");

        if (!bool) {
            sql += " where genre = ? ";
        }

        sql += " ) ";
        sql += " where ranking between ? and ? ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<KimKwangSuk> allData = new ArrayList<>();
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            if (!bool) {
                pstmt.setString(1, mode);
                pstmt.setInt(2, pageInfo.getBeginRow());
                pstmt.setInt(3, pageInfo.getEndRow());
            } else {
                pstmt.setInt(1, pageInfo.getBeginRow());
                pstmt.setInt(2, pageInfo.getEndRow());
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                KimKwangSuk bean = this.makeBean(rs);
                allData.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return allData;
    }

}