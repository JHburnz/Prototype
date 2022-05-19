package com.self.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) {

		List<Article> articles = new ArrayList<>();

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formatedNow = time.format(formatter);

		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body, formatedNow, date);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}

				System.out.println("번호 | 제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d     | %s\n", article.id, article.title);
				}
			} else if (command.equals("article detail")) {
				System.out.println("확인할 게시물 번호를 입력하세요 : ");

				int dnum = 0;

				try {
					dnum = sc.nextInt();
				} catch (Exception e) {
					System.out.println("입력을 확인해주세요!");
				}

				try {
					if (dnum == lastArticleId) {
						Article article = articles.get(lastArticleId - 1);
						System.out.printf("번호 : %d \n", article.id);
						System.out.printf("날짜 : %s %s\n", article.date, article.time);
						System.out.printf("제목 : %s \n", article.title);
						System.out.printf("내용 : %s \n", article.body);
					}
				} catch (Exception e) {

					System.out.printf("%d번 게시물은 존재하지않습니다.", dnum);

				}
			}

			else if (command.equals("article delete")) {
				int denum = 0;
				System.out.println("제거할 게시물 번호를 입력하세요 : ");
				try {
					denum = sc.nextInt();
				} catch (Exception e) {
					System.out.println("입력을 확인해주세요!");
				}

				try {
					if (denum == lastArticleId) {
						Article article = articles.remove(lastArticleId - 1);
						System.out.printf("%d번 게시물이 삭제되었습니다.", denum);
					}
				} catch (Exception e) {
					System.out.printf("%d번 게시물은 존재하지않습니다.", denum);
				}

			}

			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
			}

		}

		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}

class Article {
	int id;
	String title;
	String body;
	String time;
	LocalDate date;

	public Article(int id, String title, String body, String t, LocalDate date) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.time = t;
		this.date = date;
	}
}