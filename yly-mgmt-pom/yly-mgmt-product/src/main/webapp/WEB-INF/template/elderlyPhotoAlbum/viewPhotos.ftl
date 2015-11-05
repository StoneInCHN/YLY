        <script type="text/javascript" src="${base}/resources/jcarousel/dist/jquery.jcarousel.min.js"></script>
        <script type="text/javascript" src="${base}/resources/jcarousel/connected-carousels/jcarousel.connected-carousels.js"></script>
  
        <div class="wrapper">
            <h3>《${albumName}》</h3>
            
            <p style="text-algin:center;font-size: 15px;color: #0388A6;">${albumRemark}</p>
            
            <div class="connected-carousels">
                <div class="stage">
                    <div class="carousel carousel-stage">
                        <ul>
                        [#list elderlyPhotoes as elderlyPhotoe]
                            <li><img src="${elderlyPhotoe.url}" width="600" height="400" alt="${elderlyPhotoe.name}"></li>
                        [/#list]                          				
                        </ul>
                    </div>
                    <p class="photo-credits">
                        Photos by YLY (Total ${elderlyPhotoes?size})
                    </p>
                    <a href="#" class="prev prev-stage"><span>&lsaquo;</span></a>
                    <a href="#" class="next next-stage"><span>&rsaquo;</span></a>
                </div>

                <div class="navigation">
                    <a href="#" class="prev prev-navigation">&lsaquo;</a>
                    <a href="#" class="next next-navigation">&rsaquo;</a>
                    <div class="carousel carousel-navigation">
                        <ul>
                        [#list elderlyPhotoes as elderlyPhotoe]
                            <li><img src="${elderlyPhotoe.url}" width="50" height="50" alt="${elderlyPhotoe.name}"></li>
                        [/#list]                          						
                        </ul>
                    </div>
                </div>
            </div>
        </div>

