package course.converter.course;

import course.dto.course.CourseRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import utils.RequestMapper;

public class CourseRequestConverter {
	public static CourseRequestDTO toConverterCourse(HttpServletRequest request) {
		return RequestMapper.mapToObject(request, CourseRequestDTO.class);
	}
}
