<template>
  <div>
    <div>
    <el-row style="height: 550px;">
      <!--<search-bar></search-bar>-->
      <el-tooltip effect="dark" placement="right"
                  v-for="item in books"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.name}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.author}}</span> /
          <span>{{item.publisher}}</span> /
          <span>{{item.publishTime}}</span>
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 30px" class="book"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover">
            <img :src="item.pic" alt="封面">
          </div>
          <div class="info">
            <div class="title">
              <a href="">{{item.name}}</a>
            </div>
          </div>
          <!--<div class="author">{{item.author}}</div>-->
        </el-card>
      </el-tooltip>
    </el-row>
    </div>
    <div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-sizes="[10,20,30,40]"
        :page-size="pagination.pageSize"
        layout="total, prev, pager, next, jumper"
        :total="pagination.totalSize">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Books',
  data () {
    return {
      books: [],
      pagination: {
        totalSize: 0,
        currentPage: 1,
        pageSize: 10
      }
    }
  },
  methods: {
    handleSizeChange (val) {
      this.pagination.pageSize = val
    },
    handleCurrentChange (val) {
      this.pagination.currentPage = val
      this.bookDisplay()
    },
    bookDisplay () {
      this.axios
        .post('/bookDisplay?currentPage=' + this.pagination.currentPage + '&pageSize=' + this.pagination.pageSize)
        .then(successResponse => {
          this.books = successResponse.data.list
          this.pagination.totalSize = successResponse.data.total
        })
        .catch(failResponse => {
        })
    }
  },
  mounted: function () {
    this.bookDisplay()
  }
}
</script>

<style scoped>
  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
    /*margin: 0 auto;*/
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }
</style>
