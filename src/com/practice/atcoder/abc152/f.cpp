#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 55;
vector<pair<int,int>> edges[MAX_N];

vector<int> path, found;
void dfs(int a, int goal, int parent) {
	if(a == goal) {
		found = path;
		return;
	}
	for(pair<int,int> e : edges[a]) {
		int b = e.first;
		if(b == parent) {
			continue;
		}
		path.push_back(e.second); // edge id
		dfs(b, goal, a);
		path.pop_back();
	}
}

int main() {
	int n;
	cin >> n;
	for(int i = 0; i < n - 1; i++) {
		int a, b;
		cin >> a >> b;
		edges[a].emplace_back(b, i);
		edges[b].emplace_back(a, i);
	}
	int m;
	cin >> m;
	vector<pair<int,int>> paths;
	vector<int> containMe(n - 1);
	// containMe[e] is a mask that represents which paths
	// 								contain edge e
	for(int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		found = {-1};
		dfs(a, b, -1);
		assert(found != vector<int>{-1});
		assert(path.empty());
		for(int e : found) {
			containMe[e] |= 1 << i;
		}
	}
	vector<long long> dp(1 << m);
	// dp[mask] - number of ways to paint already-considered edges
	// so that 'mask' would represent satisfied paths
	dp[0] = 1;
	for(int e = 0; e < n - 1; e++) {
		// cout << containMe[e] << endl;
		vector<long long> new_dp(1 << m);
		for(int mask = 0; mask < (1 << m); mask++) {
			new_dp[mask] += dp[mask];
			new_dp[mask | containMe[e]] += dp[mask];
			// dp[mask] -> new_dp[mask]
			// dp[mask] -> new_dp[mask|pathSatisfiedByThisEdge]
		}
		dp = new_dp;
	}
	cout << dp[(1<<m)-1];
}
