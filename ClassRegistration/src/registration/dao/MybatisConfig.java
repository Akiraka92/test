package registration.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "mybatis_config.xml";		//Mybatis 환경설정 파일 읽기. src 경로에 저장.
		

		try {
			Reader reader = Resources.getResourceAsReader(resource);
			// xml에 저장된 모든 정보를 가지고 와서 자바에서 사용할 수 있도록 변환.
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			// sql세션팩토리는 인터페이스이기 때문에 객체를 만들 수 없고
			// 팩토리 빌드라고 하는 클래스로 (빌드 메소드를 사용하여) 객체를 생성
			
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
