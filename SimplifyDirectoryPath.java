package ProblemSetVI;

import java.util.Stack;

public class SimplifyDirectoryPath {
	
		public String simplifyPath(String a) {
			Stack stack = new Stack();
			Stack temp = new Stack();
			StringBuffer sb = new StringBuffer();
			int i = 0, sIndex = 0, eIndex = 0;

			while (i < a.length()) {
				if (a.charAt(i) == '/') {
					i++;
					sIndex = i;
					while (i < a.length() && isChar(a.charAt(i))) {
						eIndex = i;
						i++;
					}
					if (i < a.length() && a.charAt(i) == '/'
							&& a.charAt(sIndex) != '/') {
						String sNew = a.substring(sIndex, (++eIndex));
						if (sNew.equals("..")) {
							if (!stack.isEmpty())
								stack.pop();

						} else if (sNew.equals(".")) {
						} else {
							stack.push(sNew);

						}
					}
				}

			}
			String lastS = a.substring(sIndex, (++eIndex));
			if (lastS.equals("..")) {
				if (!stack.isEmpty())
					stack.pop();
			} else if (lastS.equals(".")) {

			} else if (!lastS.equals("")) {
				stack.push(lastS);
			}

		
			// System.out.println(stack.isEmpty());
			if (stack.isEmpty()) {
				sb.append("/");
				return sb.toString();
			}
			while (!stack.isEmpty()) {
				temp.add(stack.pop());
			}
			while (!temp.isEmpty()) {
				sb.append("/");
				if (!temp.isEmpty())
					sb.append(temp.pop());
			}
			// System.out.println(stack.toString());
			return sb.toString();
		}

		public boolean isChar(char a) {
			if ((a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z')
					|| (a >= '.' && a <= '.'))
				return true;
			else
				return false;
		}

		public static void main(String arg[]) {
			SimplifyDirectoryPath ss = new SimplifyDirectoryPath();
			String path = "/../abc/abc";
			// System.out.println(path.substring(2, 5));
			System.out.println(ss.simplifyPath(path));

		}

	}
