/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.converter.course;

import course.dto.course.CourseRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import utils.RequestMapper;

public class CourseRequestConverter {
	public static CourseRequestDTO toConverterCourse(HttpServletRequest request) {
		return RequestMapper.mapToObject(request, CourseRequestDTO.class);
	}
}
