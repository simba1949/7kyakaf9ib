package vip.openpark.armguard.common.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author anthony
 * @version 2023/11/28 10:45
 */
public class DOFillDataUtils {

	private static final String SYSTEM_USERNAME = "SYSTEM";
	private static final Long SYSTEM_USER_ID = 0L;
	private static final String SYSTEM_USER_CODE = "SYSTEM";


	private static final String FIELD_ENABLE = "blEnable"; // 启禁用标识
	private static final String FIELD_DELETE = "blDelete"; // 删除标识
	private static final String FIELD_VERSION = "version"; // 版本号
	private static final String FIELD_GMT_CREATE = "gmtCreate"; // 创建时间
	private static final String FIELD_CREATOR = "creator"; // 创建人
	private static final String FIELD_CREATOR_ID = "creatorId"; // 创建人 id
	private static final String FIELD_CREATOR_CODE = "creatorCode"; // 创建人 code
	private static final String FIELD_GMT_MODIFY = "gmtModify"; // 修改时间
	private static final String FIELD_MODIFIER = "modifier"; // 修改人
	private static final String FIELD_MODIFIER_ID = "modifierId"; // 修改人 id
	private static final String FIELD_MODIFIER_CODE = "modifierCode"; // 修改人 code


	private DOFillDataUtils() {
	}


	/**
	 * 补填数据————新增（系统新增）
	 *
	 * @param baseDO 待补填数据
	 */
	public static void fillData4Insert(Object baseDO) {
		fillData4Insert(baseDO, SYSTEM_USERNAME, SYSTEM_USER_ID, SYSTEM_USER_CODE);
	}

	/**
	 * 补填数据————新增
	 *
	 * @param baseDO   待补填数据
	 * @param username 用户名
	 * @param userId   用户id
	 * @param userCode 用户编码
	 */
	public static void fillData4Insert(Object baseDO,
	                                   String username, Long userId, String userCode) {
		fillDataCore(baseDO, username, userId, userCode, true, false, false);
	}

	/**
	 * 补填数据————更新（系统更新）
	 *
	 * @param baseDO 待补填数据
	 */
	public static void fillData4Update(Object baseDO) {
		fillData4Update(baseDO, SYSTEM_USERNAME, SYSTEM_USER_ID, SYSTEM_USER_CODE);
	}

	/**
	 * 补填数据————更新
	 *
	 * @param baseDO   待补填数据
	 * @param username 用户名
	 * @param userId   用户id
	 * @param userCode 用户编码
	 */
	public static void fillData4Update(Object baseDO,
	                                   String username, Long userId, String userCode) {
		fillDataCore(baseDO, username, userId, userCode, false, true, false);
	}

	/**
	 * 补填数据————逻辑删除
	 *
	 * @param baseDO 待补填数据
	 */
	public static void fillDataLogicDel(Object baseDO) {
		fillDataLogicDel(baseDO, SYSTEM_USERNAME, SYSTEM_USER_ID, SYSTEM_USER_CODE);
	}

	/**
	 * 补填数据————逻辑删除
	 *
	 * @param baseDO   待补填数据
	 * @param username 用户名
	 * @param userId   用户id
	 * @param userCode 用户编码
	 */
	public static void fillDataLogicDel(Object baseDO,
	                                    String username, Long userId, String userCode) {
		fillDataCore(baseDO, username, userId, userCode, false, false, true);
	}

	// =============================================
	// ================ private ====================
	// =============================================

	/**
	 * 补填数据核心
	 *
	 * @param baseDO     待补填数据
	 * @param username   用户名
	 * @param userId     用户id
	 * @param userCode   用户编码
	 * @param insertFlag 新增标识
	 * @param updateFlag 更新标识
	 * @param deleteFlag 删除标识
	 */
	private static void fillDataCore(Object baseDO,
	                                 String username, Long userId, String userCode,
	                                 boolean insertFlag, boolean updateFlag, boolean deleteFlag) {
		if (null == baseDO) {
			return;
		}

		Class<?> aClass = baseDO.getClass();
		Field[] declaredFields = aClass.getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);

			try {
				String fieldName = field.getName();
				Class<?> fieldType = field.getType();

				// 获取默认值
				Object fieldNameDefaultVal = getFieldNameDefaultVal(fieldName, fieldType, username, userId, userCode, insertFlag);

				// 版本号特殊处理
				if (insertFlag && FIELD_VERSION.equals(fieldName)) {
					fieldNameDefaultVal = 0L;
				}
				if (updateFlag && FIELD_VERSION.equals(fieldName)) {
					Long oldVersion = (Long) field.get(baseDO);
					fieldNameDefaultVal = (oldVersion + 1L);
				}

				// 删除标识特殊处理
				if (deleteFlag && FIELD_DELETE.equals(fieldName)) {
					fieldNameDefaultVal = (byte) 1;
				}

				// 如果设置值不为空时，设置对应的值
				if (null != fieldNameDefaultVal) {
					field.set(baseDO, fieldNameDefaultVal);
				}

			} catch (IllegalAccessException e) {
				// Consider printing the log
			}
		}
	}

	/**
	 * 根据名称和类型获取默认值
	 *
	 * @param fieldName  字段名称
	 * @param fieldType  字段类型
	 * @param username   用户名
	 * @param userId     用户 id
	 * @param userCode   用户 code
	 * @param insertFlag 新增标识
	 * @return 默认值
	 */
	private static Object getFieldNameDefaultVal(String fieldName, Class<?> fieldType,
	                                             String username, Long userId, String userCode,
	                                             boolean insertFlag) {
		// 新增时需要初始化的值
		if (insertFlag) {
			switch (fieldName) {
				case FIELD_ENABLE:
					return (byte) 1;
				case FIELD_DELETE:
					return (byte) 0;
				case FIELD_GMT_CREATE:
					return getTypeDefaultVal(fieldType);
				case FIELD_CREATOR:
					return username;
				case FIELD_CREATOR_ID:
					return userId;
				case FIELD_CREATOR_CODE:
					return userCode;
			}
		}

		switch (fieldName) {
			case FIELD_GMT_MODIFY:
				return getTypeDefaultVal(fieldType);
			case FIELD_MODIFIER:
				return username;
			case FIELD_MODIFIER_ID:
				return userId;
			case FIELD_MODIFIER_CODE:
				return userCode;
		}

		return null;
	}

	/**
	 * 获取 Class<?> 对应的默认值
	 *
	 * @param fieldType class 对应的默认值
	 * @return 默认值
	 */
	private static Object getTypeDefaultVal(Class<?> fieldType) {
		if (fieldType == Date.class) {
			return new Date();
		}
		if (fieldType == LocalDateTime.class) {
			return LocalDateTime.now();
		}
		if (fieldType == LocalDate.class) {
			return LocalDate.now();
		}
		if (fieldType == LocalTime.class) {
			return LocalTime.now();
		}

		return null;
	}
}