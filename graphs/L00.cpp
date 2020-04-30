#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Edge
{
public:
    int v = 0;
    int w = 0;
    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};
int n = 7;
vector<vector<Edge>> graph(n, vector<Edge>());

void addEdge(vector<vector<Edge>> gp, int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

int findEdge(int v1, int v2)
{
    int i = 0;
    for (i = 0; i < graph[v1].size(); i++)
    {
        if (graph[v1][i].v == v2)
            break;
    }
    return i;
}

void removeEdge(int u, int v)
{
    int idx = findEdge(u, v);
    int idx_ = findEdge(v, u);
    graph[u].erase(graph[u].begin() + idx);
    graph[v].erase(graph[v].begin() + idx_);
}

void removeVtx(int vtx)
{
    for (int i = graph[vtx].size() - 1; i >= 0; i--)
    {
        removeEdge(vtx, graph[vtx][i].v);
    }
}

void display(vector<vector<Edge>> &gp)
{
    for (int i = 0; i < gp.size(); i++)
    {
        cout << i << "--> ";
        for (Edge e : gp[i])
        {
            cout << "(" << e.v << ", " << e.w << "), ";
        }
        cout << endl;
    }
}

// questions==================================================================================
bool hasPath(int src, int des, vector<bool> &vis)
{
    if (src == des)
    {
        return true;
    }
    vis[src] = true;
    bool res = false;
    for (Edge e : graph[src])
    {
        res = res || hasPath(e.v, des, vis);
    }
    vis[src] = false;
    return res;
}

int allPath(int src, int dest, vector<bool> &vis, int w, string ans)
{
    if (src == dest)
    {
        cout << ans << dest << " @ " << w << endl;
        return 1;
    }
    vis[src] = true;
    int count = 0;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            count += allPath((e.v), dest, vis, e.w, ans + to_string(src) + " ");
    }
    vis[src] = false;
    return count;
}
void preOrder(int src, vector<bool> &vis, int w, string ans)
{
    vis[src] = true;
    cout << ans << " @ " << w << endl;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            preOrder(e.v, vis, e.w, ans + to_string(e.v) + " ");
    }
    vis[src] = false;
}

void hamintonianPath(int src, int osrc, vector<bool> &vis, int count, string ans)
{
    if (count == vis.size() - 1)
    {
        for (Edge m : graph[src])
        {
            if (m.v == osrc)
            {
                cout << "Cycle  " << ans + to_string(src) << endl;
                return;
            }
        }
        cout << "Path  " << ans + to_string(src) << endl;
        return;
    }
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            hamintonianPath(e.v, osrc, vis, count + 1, ans + to_string(src) + " ");
    }
    vis[src] = false;
}

int GCC_dfs(int src, vector<bool> vis)
{
    int count = 0;
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            count += GCC_dfs(e.v, vis);
    }
    return count + 1;
}

int GCC()
{
    vector<bool> vis(n, false);
    int count = 0;
    int maxsize = 0;
    for (int i = 0; i < n; i++)
    {
        if (!vis[i])
        {
            count++;
            maxsize = max(maxsize, GCC_dfs(i, vis));
        }
    }
    cout << maxsize << endl;
    return count;
}

void bfs(int src, vector<bool> vis)
{
    queue<pair<int, string>> que;
    int dest = 6;
    que.push({src, to_string(src) + " "});
    while (que.size() != 0)
    {
        pair<int, string> temp = que.front();
        que.pop();
        if (vis[temp.first])
        {
            cout << "Cycle: " << temp.second << endl;
            continue;
        }
        if (temp.first == dest)
        {
            cout << "Destination: " << temp.second << endl;
        }
        vis[temp.first] = true;
        for (Edge e : graph[temp.first])
            if (!vis[e.v])
                que.push({e.v, temp.second + to_string(e.v) + " "});
    }
}
// push kr de rhe hai queue mein aur baad mein hm usko true kr rhe hai function mein jaane ke baad
// true mark kro aur uske childs ko push kro
void bfs_02(int src, vector<bool> vis)
{
    queue<int> que;
    int dest = 6;
    int level = 0;
    int cycle = 0;
    que.push(src);
    while (que.size() != 0)
    {
        int p = que.size();
        while (p-- > 0)
        {
            int temp = que.front();
            que.pop();
            if (vis[temp])
            {
                cout << "Cycle No: " << cycle << "--" << temp << endl;
                cycle++;
                continue;
            }
            if (temp == dest)
            {
                cout << "Destination: " << level << "--" << temp << endl;
            }
            vis[temp] = true;
            for (Edge e : graph[temp])
                if (!vis[e.v])
                    que.push(e.v);
        }
        level++;
    }
}

// isme hm push krte hi true mark kr rhe hai ;
// push krke true mark kr do
void bfs_03(int src, vector<bool> vis)
{
    queue<int> que;
    int dest = 6;
    int level = 0;
    que.push(src);
    vis[src] = true;
    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            int idx = que.front();
            que.pop();
            if (idx == dest)
            {
                cout << "Dest : " << level << endl;
            }
            for (Edge e : graph[idx])
            {
                if (!vis[e.v])
                {
                    que.push(e.v);
                    vis[e.v] = true;
                }
            }
        }
        level++;
    }
}
void constructGraph()
{
    // for(int i=0;i<N;i++){
    //     vector<Edge*> a;
    //     graph.push_back(a);
    // }
    addEdge(graph, 0, 1, 10);
    addEdge(graph, 0, 3, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 4, 6, 3);
    addEdge(graph, 5, 6, 8);
    // addEdge(graph, 2, 5, 40);
    // removeEdge(3, 4);
    // addEdge(graph, 2, 5, 2);

    // display(graph);
    // cout << endl;
}
int main()
{
    vector<bool> vis(n, false);
    constructGraph();
    bfs_03(1, vis);
    // preOrder(0, vis, 0, to_string(0) + " ");
    // hamintonianPath(2, 2, vis, 0, "");
    return 0;
}