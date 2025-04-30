/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utils.request_mapper;

public class Target1 {
	private String string_field;
	private int int_field;
	private float float_field;
	private boolean boolean_field;

	public Target1() {
	}

	public Target1(String string_field, int int_field, float float_field, boolean boolean_field) {
		this.string_field = string_field;
		this.int_field = int_field;
		this.float_field = float_field;
		this.boolean_field = boolean_field;
	}

	public String getString_field() {
		return string_field;
	}

	public int getInt_field() {
		return int_field;
	}

	public float getFloat_field() {
		return float_field;
	}

	public boolean isBoolean_field() {
		return boolean_field;
	}
}
