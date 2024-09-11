package buddy.common.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 补填数据
 *
 * @author anthony
 * @version 2024/09/11
 * @since 2023/11/28 10:45
 */
public class FillDataUtils {
	private static final long INIT_VERSION = 0L;
	private static final long VERSION_STEP = 1L;

	private static final byte DELETE_NO_FLAG = (byte) 0;
	private static final byte DELETE_YES_FLAG = (byte) 1;

	private static final byte INIT_ENABLE = (byte) 1;

	private static final String SYSTEM_USERNAME = "SYSTEM";
	private static final Long SYSTEM_USER_ID = 0L;
	private static final String SYSTEM_USER_CODE = "SYSTEM";


	private static final String FIELD_ENABLE = "blEnable"; // 启禁用标识
	private static final String FIELD_DELETE = "blDeleted"; // 删除标识
	private static final String FIELD_VERSION = "versionNumber"; // 版本号
	private static final String FIELD_GMT_CREATE = "gmtCreate"; // 创建时间
	private static final String FIELD_CREATOR = "creator"; // 创建人
	private static final String FIELD_CREATOR_ID = "creatorId"; // 创建人 id
	private static final String FIELD_CREATOR_CODE = "creatorCode"; // 创建人 code
	private static final String FIELD_GMT_MODIFY = "gmtModify"; // 修改时间
	private static final String FIELD_MODIFIER = "modifier"; // 修改人
	private static final String FIELD_MODIFIER_ID = "modifierId"; // 修改人 id
	private static final String FIELD_MODIFIER_CODE = "modifierCode"; // 修改人 code


	private FillDataUtils() {
	}


	/**
	 * 补填数据————新增（系统新增）
	 *
	 * @param baseDO 待补填数据
	 */
	public static void forInsert(final Object baseDO) {
		forInsert(baseDO, SYSTEM_USERNAME, SYSTEM_USER_ID, SYSTEM_USER_CODE);
	}

	/**
	 * 补填数据————新增
	 *
	 * @param baseDO   待补填数据
	 * @param username 用户名
	 * @param userId   用户id
	 * @param userCode 用户编码
	 */
	public static void forInsert(final Object baseDO,
	                             final String username, final Long userId, final String userCode) {
		fillDataCore(baseDO, username, userId, userCode, true, false, false);
	}

	/**
	 * 补填数据————更新（系统更新）
	 *
	 * @param baseDO 待补填数据
	 */
	public static void forUpdate(final Object baseDO) {
		forUpdate(baseDO, SYSTEM_USERNAME, SYSTEM_USER_ID, SYSTEM_USER_CODE);
	}

	/**
	 * 补填数据————更新
	 *
	 * @param baseDO   待补填数据
	 * @param username 用户名
	 * @param userId   用户id
	 * @param userCode 用户编码
	 */
	public static void forUpdate(final Object baseDO,
	                             final String username, final Long userId, final String userCode) {
		fillDataCore(baseDO, username, userId, userCode, false, true, false);
	}

	/**
	 * 补填数据————逻辑删除
	 *
	 * @param baseDO 待补填数据
	 */
	public static void fillDataLogicDel(final Object baseDO) {
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
	public static void fillDataLogicDel(final Object baseDO,
	                                    final String username, final Long userId, final String userCode) {
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
	private static void fillDataCore(final Object baseDO,
	                                 final String username, final Long userId, final String userCode,
	                                 final boolean insertFlag, final boolean updateFlag, final boolean deleteFlag) {
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
					fieldNameDefaultVal = INIT_VERSION;
				}
				if (updateFlag && FIELD_VERSION.equals(fieldName)) {
					Long oldVersion = (Long) field.get(baseDO);
					if (null == oldVersion) {
						oldVersion = INIT_VERSION;
					}
					fieldNameDefaultVal = (oldVersion + VERSION_STEP);
				}

				// 删除标识特殊处理
				if (deleteFlag && FIELD_DELETE.equals(fieldName)) {
					fieldNameDefaultVal = DELETE_YES_FLAG;
				}

				// 如果设置值不为空时，设置对应的值
				if (null != fieldNameDefaultVal) {
					field.set(baseDO, fieldNameDefaultVal);
				}

			} catch (Exception e) {
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
	private static Object getFieldNameDefaultVal(final String fieldName, final Class<?> fieldType,
	                                             final String username, final Long userId, final String userCode,
	                                             final boolean insertFlag) {
		// 新增时需要初始化的值
		if (insertFlag) {
			switch (fieldName) {
				case FIELD_ENABLE:
					return INIT_ENABLE;
				case FIELD_DELETE:
					return DELETE_NO_FLAG;
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
	private static Object getTypeDefaultVal(final Class<?> fieldType) {
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