package registration.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "mybatis_config.xml";		//Mybatis ȯ�漳�� ���� �б�. src ��ο� ����.
		

		try {
			Reader reader = Resources.getResourceAsReader(resource);
			// xml�� ����� ��� ������ ������ �ͼ� �ڹٿ��� ����� �� �ֵ��� ��ȯ.
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			// sql�������丮�� �������̽��̱� ������ ��ü�� ���� �� ����
			// ���丮 ������ �ϴ� Ŭ������ (���� �޼ҵ带 ����Ͽ�) ��ü�� ����
			
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
