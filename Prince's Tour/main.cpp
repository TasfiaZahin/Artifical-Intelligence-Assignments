#include<bits/stdc++.h>
using namespace std;

struct position
{
    int x;
    int y;

};

bool checkGrid(int grid[8][8],int n)
{
    //cout<<"\n"<<"check:\n";
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            //cout<<grid[i][j]<<" ";
            if(grid[i][j] == 0)
            {
                return false;
            }
        }
        //cout<<"\n";
    }
    return true;
}

bool startTour(struct position index, int grid[8][8], int n)
{
    int x = index.x;
    int y = index.y;
    //cout<<"position: "<<x<<","<<y<<"\n";

    bool c = checkGrid(grid, n);
    if(c == true)
    {
        return true;
    }

    position leftCorner,bottom,right;
    leftCorner.x = x-1;
    leftCorner.y = y-1;
    bottom.x = x+1;
    bottom.y = y;
    right.x = x;
    right.y = y+1;

    position nextPos[3];
    nextPos[0] = leftCorner;
    nextPos[1] = bottom;
    nextPos[2] = right;

    for(int i=0;i<3;i++)
    {
        if(nextPos[i].x >=0 && nextPos[i].x<n && nextPos[i].y>=0 && nextPos[i].y<n && grid[nextPos[i].x][nextPos[i].y] == 0)
        {
            grid[nextPos[i].x][nextPos[i].y] = 1;
            bool test = startTour(nextPos[i],grid,n);
            if(test == true)
            {
                return true;
            }
            else
            {
                grid[nextPos[i].x][nextPos[i].y] = 0;

            }
        }

    }
    //cout<<"came in false\n";
    return false;
}

int main()
{
    while(1)
    {
        int n;
        cout<<"Enter value of n\n";
        cin>>n;
        //cout<<n<<"\n";

        if(n == 0)
        {
            break;
        }

        int grid[8][8];
        int solGrid [8][8];

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                grid[i][j] = 0;
                solGrid[i][j] = 0;
            }
        }

        struct position index;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                index.x = i;
                index.y = j;
                //cout<<"hi";
                grid[i][j] = 1;
                bool test = startTour(index, grid, n);

                if(test == true)
                {
                    solGrid[i][j] = 1;
                }

                for(int i=0;i<8;i++)
                {
                    for(int j=0;j<8;j++)
                    {
                        grid[i][j] = 0;
                    }
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                cout<<solGrid[i][j]<<" ";
            }
            cout<<"\n";
        }
        cout<<"\n";
    }
    return 0;
}
