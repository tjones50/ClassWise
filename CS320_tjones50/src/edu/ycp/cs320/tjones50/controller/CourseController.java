package edu.ycp.cs320.tjones50.controller;

import java.util.ArrayList;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class CourseController {

	private Course course;;
	private DerbyDatabase database = new DerbyDatabase();

	public CourseController() {

	}

	public CourseController(String courseName) {
		this.course = database.getCourseByName(courseName);
		this.course.setArrAdvice(database.getCourseAdviceList(course));
	}

	public void setCourse(Course course) {
		this.course = course;
		this.course.setArrAdvice(database.getCourseAdviceList(course));
	}

	public void setCourseByName(String courseName) {
		this.course = database.getCourseByName(courseName);
	}

	public Course getCourse() {
		return this.course;
	}

	public void computeAveRating() {
		ArrayList<Advice> arrAdvice = course.getArrAdvice();
		double difficultySum = 0;
		double instructionSum = 0;
		double suppliesCostSum = 0;
		double enjoymentSum = 0;

		for (int i = 0; i < arrAdvice.size(); i++) {
			difficultySum += arrAdvice.get(i).getAdviceRating().getDifficulty();
			instructionSum += arrAdvice.get(i).getAdviceRating().getInstruction();
			suppliesCostSum += arrAdvice.get(i).getAdviceRating().getSuppliesCost();
			enjoymentSum += arrAdvice.get(i).getAdviceRating().getEnjoyment();
		}

		difficultySum = difficultySum / arrAdvice.size();
		instructionSum = instructionSum / arrAdvice.size();
		suppliesCostSum = suppliesCostSum / arrAdvice.size();
		enjoymentSum = enjoymentSum / arrAdvice.size();
		Rating aveRatings = new Rating(difficultySum, instructionSum, suppliesCostSum, enjoymentSum);
		course.setAveRatings(aveRatings);
	}

	public void computeAveGrade() {
		ArrayList<Advice> arrAdvice = course.getArrAdvice();
		double aveGrade = 0;

		for (int i = 0; i < arrAdvice.size(); i++) {
			aveGrade += arrAdvice.get(i).getGradeReceived();
		}

		aveGrade = aveGrade / arrAdvice.size();
		course.setAveGrade(aveGrade);
	}

	// adds advice
	public void addAdviceAndRatingToCourse(User user, String semester, String professor, double grade, int year,
			String text, double difficulty, double instruction, double suppliesCost, double enjoyment) {
		int adviceId = database.addAdviceToCourse(user, course, semester, professor, grade, year, text);
		database.insertRating(adviceId, difficulty, instruction, suppliesCost, enjoyment);
	}

	public void SortAdvice(String sort) {
		if (sort.equals("Newest")) {
			this.course.setArrAdvice(database.getCourseAdviceList(course));
		} else if (sort.equals("Oldest")) {
			this.course.setArrAdvice(database.getCourseAdviceListSortedByOldest(course));
		} else if (sort.equals("Grade Received")) {
			this.course.setArrAdvice(database.getAdviceListSortedByGrade(course));
		} else if (sort.equals("Student GPA")) {
			this.course.setArrAdvice(database.getAdviceListSortedByGPA(course));
		} else if (sort.equals("Semester Taken")) {
			this.course.setArrAdvice(database.getAdviceListSortedBySemester(course));
		} else if (sort.equals("Student Major")) {
			this.course.setArrAdvice(database.getAdviceListSortedByMajor(course));
		} else if (sort.equals("Year Taken")) {
			this.course.setArrAdvice(database.getAdviceListSortedByYear(course));
		} else if (sort.equals("Professor")) {
			this.course.setArrAdvice(database.getAdviceListSortedByProfessor(course));
		} else if (sort.equals("Course Difficulty")) {
			this.course.setArrAdvice(database.getAdviceListSortedByDifficulty(course));
		} else if (sort.equals("Course Instruction")) {
			this.course.setArrAdvice(database.getAdviceListSortedByInstruction(course));
		} else if (sort.equals("Course Enjoyment")) {
			this.course.setArrAdvice(database.getAdviceListSortedByEnjoyment(course));
		} else if (sort.equals("Price of Supplies")) {
			this.course.setArrAdvice(database.getAdviceListSortedBySupplyCost(course));
		}

	}
}
