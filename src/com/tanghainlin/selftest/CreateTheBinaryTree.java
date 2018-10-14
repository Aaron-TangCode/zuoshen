package com.tanghainlin.selftest;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 
 * 功能：
 * 前序遍历
 * 中序遍历
 * 后序遍历
 * 通过一个数组来创建一颗二叉树
 * DFS
 * BFS
 * @author aaron
 *
 */
public class CreateTheBinaryTree {
	public static class Node{
		Node left;
		Node right;
		int value;
		public Node(int value) {
			super();
			this.value = value;
		}
		
	}
	
	/**
	 * 通过一个数组来创建一颗二叉树
	 * 数组中遇到0，就为空，不创建节点
	 * @param arr
	 * @param index
	 * @return
	 */
	public static Node createBinaryTreeByArray(int[] arr,int index) {
		if(index<arr.length) {
			int value = arr[index];
			if(value!=0) {
				Node node = new Node(value);
				node.left = createBinaryTreeByArray(arr, index*2);
				node.right = createBinaryTreeByArray(arr, index*2+1);
				return node;
			}
		}
		return null;
	}
	/**
	 * 先序遍历：中-左-右
	 * @param root
	 */
	public static void preSearch(Node root) {
		if(root==null) {
			return;
		}
		System.out.print(root.value+" ");
		preSearch(root.left);
		preSearch(root.right);
	}
	/**
	 * 中序遍历：左-中-右
	 * @param root
	 */
	public static void midSearch(Node root) {
		if(root==null) {
			return;
		}
		midSearch(root.left);
		System.out.print(root.value+" ");
		midSearch(root.right);
	}
	/**
	 * 后序遍历：左-右-中
	 * @param root
	 */
	public static void posSearch(Node root) {
		if(root==null) {
			return;
		}
		posSearch(root.left);
		posSearch(root.right);
		System.out.print(root.value+" ");
	}
	/**
	 * DFS:深度优先遍历，用栈来实现,
	 * DFS:和先序遍历一样
	 * @param root
	 */
	public static void DFS(Node root) {
		if(root==null) {
			return;
		}
		
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while(stack.isEmpty()==false) {
			Node cur = stack.pop();
			System.out.print(cur.value+" ");
			if(cur.right!=null) {
				
				stack.push(cur.right);
			}
			if(cur.left!=null) {
				stack.push(cur.left);
			}
		}
	}
	/**
	 * BFS广度优先遍历
	 * @param root
	 */
	public static void BFS(Node root) {
		if(root==null) {
			return;
		}
		LinkedList<Node> queue = new LinkedList<>();
		
		queue.add(root);
		while(queue.isEmpty()==false) {
			Node cur = queue.remove();
			System.out.print(cur.value+" ");
			if(cur.left!=null) {
				queue.add(cur.left);
			}
			if(cur.right!=null) {
				queue.add(cur.right);
			}
		}
	}
	/**
	 * 二叉树的最大深度
	 * @param root
	 * @return
	 */
	public static int maxDepth(Node root) {
		if(root==null) {
			return 0;
		}
//		int leftMaxDepth = maxDepth(root.left)+1;
//		int rightMaxDepth = maxDepth(root.right)+1;
//		
//		return Math.max(leftMaxDepth, rightMaxDepth);
		return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
	}
	/**
	 * 二叉树的最小深度
	 * @param root
	 * @return
	 */
	public static int minDepth(Node root) {
		if(root==null) {
			return 0;
		}
		if(root.left==null) {
			return minDepth(root.right)+1;
		}
		if(root.right==null) {
			return minDepth(root.left)+1;
		}
		int leftMinDepth = minDepth(root.left)+1;
		int rightMinDepth = minDepth(root.right)+1;
		
		return Math.min(leftMinDepth, rightMinDepth);
	}
	/**
	 * 判断一棵树是否是平衡二叉树
	 * @param root
	 * @return
	 */
	public static boolean balanceTree(Node root) {
		if(root==null) {
			return true;
		}else if(Math.abs(maxDepth(root.left)-maxDepth(root.right))>1) {
			return false;
		}else {
			return balanceTree(root.left)&&balanceTree(root.right);
		}
		
	}
	/**
	 * 二叉树的翻转
	 * @param root
	 */
	public static Node reverse(Node root) {
		if(root==null) {
			return null;
		}
		Node tmp = root.left;
		root.left = reverse(root.right);
		root.right = reverse(tmp);
		return root;
	}
	/** 
     *                  13
     *                 /  \
     *               65    5
     *              /  \    \
     *             97  25   37
     *            /    /\   /
     *           22   4 28 32
     */
	public static void main(String[] args) {
		int[] arr = new int[] {0,13,65,5,97,25,0,37,22,0,4,28,0,0,32,0};
//		int[] arr = new int[] {0,13,65,5,0};
		Node root = createBinaryTreeByArray(arr, 1);
		System.out.print("先序遍历："+"  ");
		preSearch(root);
		System.out.println();
		System.out.print("中序遍历："+"  ");
		midSearch(root);
		System.out.println();
		System.out.print("后序遍历："+"  ");
		posSearch(root);
		System.out.println();
		System.out.print("DFS："+"  ");
		DFS(root);
		System.out.println();
		System.out.print("BFS："+"  ");
		BFS(root);
		System.out.println();
		System.out.print("树的最大深度："+maxDepth(root));
		System.out.println();
		System.out.print("树的最小深度："+minDepth(root));
		System.out.println();
		System.out.print("该树是否是平衡二叉树" + balanceTree(root));
		System.out.println();
		System.out.print("二叉树翻转");
		reverse(root);
		System.out.println("先序遍历：");
		preSearch(root);
		
	}
}
