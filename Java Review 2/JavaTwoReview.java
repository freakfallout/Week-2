package javaTwoReview;

import java.io.*;

public class JavaTwoReview {

	public static void main(String[] args) 
	{
		int[][] dailyTemps = new int[31][3];
		
		try {
			File file = new File(System.getProperty("user.home") + "\\Documents\\SLCDecember2020Temperatures.csv");
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr); 

			try 
			{
				String s;
				int c = 0;
				do
				{
					s = null;
					s = reader.readLine();
					if (s != null)
					{
						String[] splits = s.split(",");
						
						for (int i = 0; i < splits.length; i++)
						{
							dailyTemps[c][i] = Integer.parseInt(splits[i]);
						}
						c++;
					}
				} while (s != null);
				
			} catch (IOException e)
			{

				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		int lowTempDate = 0;
		int lowestTemp = 100;
		
		
		int hiTempDate = 0;
		int highestTemp = 0;
		float avgHi = 0;
		float avgLow = 0;
		
		
		try 
		{
			File newFile = new File(System.getProperty("user.home") + "\\Documents\\TemperatureReports.txt");
			newFile.createNewFile();
			
			FileWriter fw = new FileWriter(newFile);
			
			BufferedWriter writer = new BufferedWriter(fw);
			
			writer.write(HorzLine(50, "-"));
			writer.write("\n");
			
			String writeString ="December 2020: Temperatures in Utah";
			writer.write(writeString);
			writer.write("\n");
			
			System.out.println(writeString);
			
			writer.write(HorzLine(50, "-"));
			writer.write("\n");
			
			writeString = "Day\tHigh\tLow\tVariance";
			writer.write(writeString);
			writer.write("\n");
			
			System.out.println(writeString);
			for (int i = 0; i < dailyTemps.length; i++) 
			{
				
				avgHi += dailyTemps[i][1]; 
				avgLow += dailyTemps[i][2]; 
				
				
				if (dailyTemps[i][1] > highestTemp)
				{
					highestTemp = dailyTemps[i][1];
					hiTempDate = dailyTemps[i][0];
				}
				if (dailyTemps[i][2] < lowestTemp)
				{
					lowestTemp = dailyTemps[i][2];
					lowTempDate = dailyTemps[i][0];
				}
				
				
				for (int j = 0; j < dailyTemps[i].length; j++) 
				{

					writeString = dailyTemps[i][j] + "\t";
					writer.write(writeString);
					System.out.print(writeString);
				}
				
				writeString = (dailyTemps[i][1] -  dailyTemps[i][2]) +  "\t";
				writer.write(writeString);
				
				writer.write("\n");
				System.out.print(writeString);
				System.out.println();
			}
			
			
			writer.write(HorzLine(50, "-"));
			writer.write("\n");
			writeString = "December Highest Temperature: 12/" + hiTempDate + ": " + highestTemp + " Average Hi: " + String.format("%.1f", (avgHi / 31f));
			writer.write(writeString);
			writer.write("\n");
			System.out.println(writeString);
			
			writeString ="December Lowest Temperature: 12/" + lowTempDate + ": " + lowestTemp + " Average Low: " + String.format("%.1f", (avgLow / 31f));
			writer.write(writeString);
			writer.write("\n");
			System.out.println(writeString);
			
			writer.write(HorzLine(50, "-"));
			writer.write("\n");
			writer.write("Graph");
			writer.write("\n");
			
			System.out.println("Graph");
			writer.write(HorzLine(50, "-"));
			writer.write("\n");

			int temp = 5;
			writer.write("         1    ");
			System.out.print("         1    ");
			for (int i = 0; i < 11; i++) 
			{
				writer.write(String.format("%1$-5s", temp));
				System.out.print(String.format("%1$-5s", temp));
				temp += 5;
			}
			System.out.println("");
			writer.write("\n");
			
			writer.write("         |");
			System.out.print("         |");
			for (int i = 0; i < 11; i++) 
			{
				writer.write("    |");
				System.out.print("    |");
			}
			System.out.println();
			writer.write("\n");
			
			writer.write(HorzLine(50, "-"));
			writer.write("\n");
			for (int i = 0; i < dailyTemps.length; i++) 
			{
				writeString = String.format("%1$-3s  ", dailyTemps[i][0])  + "Hi  ";
				writer.write(writeString);
				System.out.print(writeString);
				writer.write((HorzLine(dailyTemps[i][1], "+")));
				writer.write("\n");
				
				writeString = "     " + "Lo  ";
				writer.write(writeString);
				System.out.print(writeString);
				
				writer.write(HorzLine(dailyTemps[i][2], "-"));
				writer.write("\n");
			}
			
			writer.write(HorzLine(50, "-"));
			writer.write("\n");

			writer.write("         |");
			System.out.print("         |");
			for (int i = 0; i < 11; i++) 
			{
				writer.write("    |");
				System.out.print("    |");
			}
			System.out.println();
			writer.write("\n");
			
			temp = 5;
			writer.write("         1    ");
			System.out.print("         1    ");
			for (int i = 0; i < 11; i++) 
			{
				writer.write(String.format("%1$-5s", temp));
				System.out.print(String.format("%1$-5s", temp));
				temp += 5;
			}
			System.out.println("");
			writer.write("\n");
			
			writer.write(HorzLine(50, "-"));
			
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
