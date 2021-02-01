package javaThreeReview;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class JavaSqlReview 
{


	public static void main(String[] args)
    {

        String connectionString = "jdbc:mysql://127.0.0.1:3306/practice";

        String dbLogin = "root";
        String dbPassword = "12345";
        

        int rows = 0;
        String monthChoice = null;
        int[][] dailyTemps = null;

        Connection conn = null;
        try 
        {

            conn = DriverManager.getConnection(connectionString, dbLogin, dbPassword);

            if (conn != null) 
            {
            
            	Scanner sc = new Scanner(System.in);
            	int month = 0;
            	
            	do 
            	{
            		
            	System.out.print("Would you like the November (11) or December (12) report: ");
            	month = sc.nextInt();
            	
            	} while (month != 11 && month != 12);
            	
            	monthChoice = month == 11 ? "November" : "December";
            	
                String sql = "SELECT * FROM temperatures WHERE month = " + month;
            	Statement st = conn.createStatement();

                ResultSet res = st.executeQuery("select count(*) from temperatures WHERE month = " + month);
                res.next();
                rows = res.getInt("count(*)");
            	dailyTemps = new int[rows][5];

            	ResultSet rs = st.executeQuery(sql);
            	int index = 0;
            	while(rs.next())
            	{
            		dailyTemps[index][0] = Integer.parseInt(rs.getString("month"));
            		dailyTemps[index][1] = Integer.parseInt(rs.getString("day"));
            		dailyTemps[index][2] = Integer.parseInt(rs.getString("year"));
            		dailyTemps[index][3] = Integer.parseInt(rs.getString("hi"));
            		dailyTemps[index][4] = Integer.parseInt(rs.getString("lo"));
            		index++;
            	}
            }
        } 
        // The catch will execute if the try section fails
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        
        int lowTempDay = 0, lowTempMonth = 0;
		int lowestTemp = 100;
		
		int indexMonth = 0, indexDay = 1, indexYear = 2, indexHi = 3, indexLo = 4; 
		
		
		int hiTempDay = 0, hiTempMonth = 0;
		int highestTemp = 0;
		float avgHi = 0;
		float avgLow = 0;
		
		
		try 
		{
			File newFile = new File(System.getProperty("user.home") + "\\Documents\\TemperatureReportFromDB.txt");
			if (newFile.exists())
				newFile.delete();
			
			newFile.createNewFile();
			
			FileWriter fw = new FileWriter(newFile);
			
			BufferedWriter writer = new BufferedWriter(fw);
			
			writer.write(HorzLine(70, "-"));
			writer.write("\n");
			
			String writeString = monthChoice + " 2020: Temperatures in Utah";
			writer.write(writeString);
			writer.write("\n");
			
			System.out.println(writeString);
			
			writer.write(HorzLine(70, "-"));
			writer.write("\n");
			
			writeString = "Date\t\tHigh\tLow\tVariance";
			writer.write(writeString);
			writer.write("\n");
			
			System.out.println(writeString);
			for (int i = 0; i < dailyTemps.length; i++) 
			{
				
				avgHi += dailyTemps[i][indexHi]; 
				avgLow += dailyTemps[i][indexLo]; 
				
				
				if (dailyTemps[i][indexHi] > highestTemp)
				{
					highestTemp = dailyTemps[i][indexHi];
					hiTempDay = dailyTemps[i][indexDay];
					hiTempMonth = dailyTemps[i][indexMonth];
				}
				if (dailyTemps[i][indexLo] < lowestTemp)
				{
					lowestTemp = dailyTemps[i][indexLo];
					lowTempDay = dailyTemps[i][indexDay];
					lowTempMonth = dailyTemps[i][indexMonth];
					
				}
				
				writeString = dailyTemps[i][indexMonth] + "/" + dailyTemps[i][indexDay] + "/" + dailyTemps[i][indexYear] + "\t" + dailyTemps[i][indexHi] + "\t" + dailyTemps[i][indexLo] + "\t";
				writer.write(writeString);
				System.out.print(writeString);
					
				writeString = (dailyTemps[i][indexHi] -  dailyTemps[i][indexLo]) +  "\t";
				writer.write(writeString);
				
				writer.write("\n");
				System.out.print(writeString);
				System.out.println();
			}
			
			
			writer.write(HorzLine(70, "-"));
			writer.write("\n");
			writeString = monthChoice + " Highest Temperature: " + hiTempMonth + "/" + hiTempDay + ": " + highestTemp + " Average Hi: " + String.format("%.1f", (avgHi / (float)rows));
			writer.write(writeString);
			writer.write("\n");
			System.out.println(writeString);
			
			writeString = monthChoice + " Lowest Temperature: " + lowTempMonth +"/" + lowTempDay + ": " + lowestTemp + " Average Low: " + String.format("%.1f", (avgLow / (float)rows));
			writer.write(writeString);
			writer.write("\n");
			System.out.println(writeString);
			
			writer.write(HorzLine(70, "-"));
			writer.write("\n");
			writer.write("Graph");
			writer.write("\n");
			
			System.out.println("Graph");
			writer.write(HorzLine(70, "-"));
			writer.write("\n");

			int temp = 5;
			writeString = "      \t1    ";
			writer.write(writeString);
			System.out.print(writeString);
			for (int i = 0; i < 11; i++) 
			{
				writer.write(String.format("%1$-5s", temp));
				System.out.print(String.format("%1$-5s", temp));
				temp += 5;
			}
			System.out.println("");
			writer.write("\n");
			
			writeString = "\t|";
			writer.write(writeString);
			System.out.print(writeString);
			for (int i = 0; i < 11; i++) 
			{
				writer.write("    |");
				System.out.print("    |");
			}
			System.out.println();
			writer.write("\n");
			
			writer.write(HorzLine(70, "-"));
			writer.write("\n");
			for (int i = 0; i < dailyTemps.length; i++) 
			{
				writeString = String.format("%1$-5s", dailyTemps[i][indexDay]) + "Hi  ";
				writer.write(writeString);
				System.out.print(writeString);
				writer.write((HorzLine(dailyTemps[i][indexHi], "+")));
				writer.write("\n");
				
				writeString = "     Lo  ";
				writer.write(writeString);
				System.out.print(writeString);
				
				writer.write(HorzLine(dailyTemps[i][indexLo], "-"));
				writer.write("\n");
			}
			
			writer.write(HorzLine(70, "-"));
			writer.write("\n");

			writeString = "\t|";
			writer.write(writeString);
			System.out.print(writeString);
			for (int i = 0; i < 11; i++) 
			{
				writer.write("    |");
				System.out.print("    |");
			}
			System.out.println();
			writer.write("\n");
			
			temp = 5;
			writeString = "    \t1    ";
			writer.write(writeString);
			System.out.print(writeString);
			for (int i = 0; i < 11; i++) 
			{
				writer.write(String.format("%1$-5s", temp));
				System.out.print(String.format("%1$-5s", temp));
				temp += 5;
			}
			System.out.println("");
			writer.write("\n");
			
			writer.write(HorzLine(70, "-"));
			
			writer.close();
			
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		

		
	}
	
	
	public static String HorzLine(int length, String s)
	{
		StringBuilder sb = new StringBuilder((length + 1) * s.length());
		
		for (int i = 0; i < length; i++) 
		{
			sb.append(s);
			System.out.print(s);
		}
		System.out.println("");
		return sb.toString();
	}
        
        
        
        
	
	

}
